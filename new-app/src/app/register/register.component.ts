import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MustMatch, PasswordValidator } from '../validationReg/must-match.validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  //message:any="";
  user: any;
  msg: any;
  users: any;
  registerForm: FormGroup;
  submitted = false;
  taken = false;
  etaken = false;
  eotp: number;
  otp: number;

  constructor(private router: Router, private service: UserserviceService, private toastr: ToastrService, private formBuilder: FormBuilder) {
    this.user = { loginId: '', email: '', password: '' };
    //this.message=service.getMessage();
  }

  ngOnInit(): void {
    this.service.getAllUsers().subscribe((result: any) => { console.log(result); this.users = result; });
    this.registerForm = this.formBuilder.group({
      loginId: ['', [Validators.required, Validators.minLength(5)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), PasswordValidator.strong]],
      confirmPassword: ['', Validators.required]
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
    this.msg = JSON.parse(localStorage.getItem("msg"));
    if (this.msg) {
      this.toastr.error("Not Registered!!");
    }
    this.msg = "";
    localStorage.setItem('msg', JSON.stringify(''));
    //console.log(this.user.loginId);
  }

  register(regForm: any) {
    if (regForm.password != regForm.password2) {
      this.toastr.error("Password not Matching");
    } else {
      this.service.registerUser(this.user).subscribe((result: any) => { console.log(this.user); });
      localStorage.setItem('msg', JSON.stringify('msg'));
      this.router.navigate(['login']);
    }
  }
  get f() { return this.registerForm.controls; }

  userNameTaken(): any {
    var searchText = this.registerForm.value.loginId;
    searchText = searchText.toLowerCase();
    return this.users.filter(item => {
      if (item && item['loginId']) {
        return item['loginId'].toLowerCase() === searchText;
      }
      return false;
    });
  }

  emailTaken(): any {
    var searchText = this.registerForm.value.email;
    searchText = searchText.toLowerCase();
    return this.users.filter(item => {
      if (item && item['email']) {
        return item['email'].toLowerCase() === searchText;
      }
      return false;
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      if (this.userNameTaken().length > 0) {
        this.taken = true;
        this.msg = this.user.loginId;
      }
      if (this.emailTaken().length > 0) {
        this.submitted = false;
        this.taken = false;
        this.toastr.warning("Account Exists with the mail, " + this.user.email);
        this.registerForm.reset();
      }
      return;
    }
    if (this.emailTaken().length > 0) {
      this.submitted = false;
      this.taken = false;
      this.toastr.warning("Account Exists with the mail, " + this.user.email);
      this.registerForm.reset();
    }
    if (this.userNameTaken().length > 0) {
      this.taken = true;
      this.msg = this.user.loginId;
      console.log(this.userNameTaken());
      return;
    }
    this.service.sendMail(this.user.email, this.user.loginId).subscribe((result: any) => { console.log(result); this.otp = result });

    document.getElementById('id01').style.display = 'block';

    //    this.otp=1234;
    /*this.service.registerUser(this.user).subscribe((result: any) => { console.log(this.user); });
    localStorage.setItem('msg', JSON.stringify('msg'));
    this.router.navigate(['login']);*/
  }

  verifyOTP() {
    console.log(this.otp);
    console.log(this.eotp);
    console.log(this.eotp === this.otp);
    if (this.eotp === this.otp) {
      this.taken = false;
      console.log("Registered");
      document.getElementById('id01').style.display = 'none';
      this.service.registerUser(this.user).subscribe((result: any) => { console.log(this.user); });
      localStorage.setItem('msg', JSON.stringify('msg'));
      this.router.navigate(['login']);
    } else {
      this.taken = true;
      this.eotp = null;
    }
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
}
