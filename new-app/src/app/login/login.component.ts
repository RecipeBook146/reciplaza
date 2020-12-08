import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { ToastrService } from 'ngx-toastr';
import { SocialAuthService } from "angularx-social-login";
import { SocialUser, GoogleLoginProvider } from "angularx-social-login";
 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  msg: any = "";
  message: any = "";
  user: SocialUser;
  user2: any;
  gUser: any;

  constructor(private router: Router, private service: UserserviceService, private toastr: ToastrService, private authService: SocialAuthService) {
    this.user2 = { loginId: '', email: '', password: '' };
  }

  ngOnInit(): void {
    //this.message = this.service.getMessage();
    this.authService.authState.subscribe((user) => {
      this.user = user;
      console.log(this.user);
      this.user2.loginId = this.user.id;
      this.user2.email = this.user.email;
      this.user2.password = "";

      this.service.signInWithGoogle(this.user2).subscribe((result: any) => {
        this.gUser = result;
        console.log(this.gUser);
        if (this.gUser) {
          if (!this.gUser.password) {
            localStorage.setItem("googleUser", this.user.name);
          }
          localStorage.setItem("googleUser",this.gUser.loginId);
          this.service.setLoggedId(this.gUser.loginId);
        } else {
          localStorage.setItem("googleUser", this.user.name);
          this.service.setLoggedId(this.user.id);
        }
        this.service.setUserLoggedIn();

        console.log(this.service.getLoggedId());
        this.router.navigate(['allRecipe']);
      });

    })
    this.msg = JSON.parse(localStorage.getItem("msg"));
    if (this.msg) {
      this.toastr.success("Successfully Registered!!");
    }
    localStorage.setItem('msg', JSON.stringify(''));
  }

  loginSubmit(loginForm: any): void {
    if (loginForm.loginId === "admin" && loginForm.password === "admin") {
      this.service.setAdminLoggedIn();
      this.router.navigate(['addProd']);
    } else {
      this.service.verifyLogin(loginForm.loginId).subscribe((result: any) => {
        console.log(result);
        this.message = result;
        if (this.message) {
          if (this.message.password === loginForm.password) {
            this.service.setUserLoggedIn();
            this.service.setLoggedId(loginForm.loginId);
            this.router.navigate(['allRecipe']);
            console.log(this.service.getUserLogged());
          } else {
            this.toastr.error("Invalid Credentials");
          }
        } else {
          //this.toastr.error("Invalid Credentials");
          localStorage.setItem('msg', JSON.stringify('msg'));
          this.router.navigate(['register']);
        }
      });
    }
  }
  navigate(): any {
    this.router.navigate(['register']);
  }
  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);

  }

}
