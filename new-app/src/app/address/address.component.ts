import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
declare var jQuery: any;

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {
  address: any;
  loginId: any;
  allAddress: any;
  editAddress: any;
  demo: any;
  flag: boolean;
  AddressForm: FormGroup;
  submitted = false;

  constructor(private service: UserserviceService, private toastr: ToastrService, private router: Router, private formBuilder: FormBuilder) {
    this.address = { hno: '', street: '', city: '', state: '', landmark: '', pincode: '', user: { loginId: '', email: '', password: '' } };
    this.editAddress = { hno: '', street: '', city: '', state: '', landmark: '', pincode: '', user: { loginId: '', email: '', password: '' } };
    this.demo = { hno: '', street: '', city: '', state: '', landmark: '', pincode: '' };
    this.flag = false;
  }

  ngOnInit(): void {
    this.loginId = this.service.getLoggedId();
    this.service.getAddress(this.loginId).subscribe((result: any) => { console.log(result); this.allAddress = result; });
    this.AddressForm = this.formBuilder.group({
      hno: ['', Validators.required],
      street: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      landmark: ['', Validators.required],
      pincode: ['', Validators.required]
    });
    //console.log('Address',this.allAddress);
  }
  get f() { return this.AddressForm.controls; }

  showEditPopup() {
    jQuery('#addressModal').modal('show');
  }
  addAddress() {
    this.submitted = true;
    if (this.AddressForm.invalid) {
      return;
    }
    this.address.user.loginId = this.loginId;
    console.log(this.address);
    this.service.addAddress(this.address).subscribe((result: any) => { console.log(this.address); });
    jQuery('#addressModal').modal('hide');
    this.submitted=false;
    this.toastr.success('Added Address');
    this.flag = true;
    this.ngOnInit();
    this.ngOnInit();
  }
  showUpdatePopup(address: any) {
    /*this.editAddress.id=address.id;
    this.editAddress.hno=address.hno;
    this.editAddress.street=address.street;
    this.editAddress.city=address.city;
    this.editAddress.state=address.state;
    this.editAddress.landmark=address.landmark;
    this.editAddress.user.loginId=this.loginId;*/
    /*console.log(address);
    console.log(this.editAddress);*/
    this.demo = address;
    jQuery('#editAddressModal').modal('show');
  }
  deleteAddress(address: any) {
    // console.log(address);
    this.service.deleteAddress(address).subscribe((result: any) => {
      const i = this.allAddress.findIndex((element) => {
        return address.id === element.id;
      });
      this.allAddress.splice(i, 1);
    });
    //console.log(address);
  }
  updateAddress() {
    this.editAddress.id = this.demo.id;
    this.editAddress.hno = this.demo.hno;
    this.editAddress.street = this.demo.street;
    this.editAddress.city = this.demo.city;
    this.editAddress.state = this.demo.state;
    this.editAddress.landmark = this.demo.landmark;
    this.editAddress.user.loginId = this.loginId;
    jQuery('#editAddressModal').modal('hide');
    this.service.updateAddress(this.editAddress).subscribe((result: any) => { console.log(result); });
  }
  back() {
    var stat=localStorage.getItem("show1");
    console.log(stat);
    if (this.flag || stat) {
      localStorage.setItem("show1","");
      localStorage.setItem("show", "show");
    }
    this.router.navigate(['viewcart']);
  }
}
