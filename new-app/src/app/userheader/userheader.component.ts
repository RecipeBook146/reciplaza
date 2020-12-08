import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';
import { SocialAuthService } from 'angularx-social-login';

@Component({
  selector: 'app-userheader',
  templateUrl: './userheader.component.html',
  styleUrls: ['./userheader.component.css']
})
export class UserheaderComponent implements OnInit {
  loginId: any;
  //products:any;
  len: any;
  temp = [];

  constructor(private service: UserserviceService, private router: Router, private authService: SocialAuthService) {
    if (localStorage.getItem("googleUser")) {
      this.loginId = localStorage.getItem("googleUser")

    } else {
      this.loginId = service.getLoggedId();
    }
  }

  ngOnInit(): void {
    this.service.getCartProducts(this.loginId).subscribe((result: any) => { console.log(result); this.len = result.length; });
    this.service.getForCart().subscribe((result: any) => { this.temp.push(result); console.log(this.temp); });
    //this.len+=this.temp.length;
  }
  logout(): any {
    this.service.setUserLoggedOut();
    if (localStorage.getItem("googleUser")) {
      this.authService.signOut();
      localStorage.setItem("googleUser", "");
    }
    this.router.navigate(['']);
  }
  addRecipe(): any {
    this.router.navigate(['addRecipe']); 
  }
  allRecipe(): any {
    this.router.navigate(['allRecipe']);
  }
  getfav(): any {
    this.router.navigate(['getfav']);
  }
  getmyrecipes(): any {
    this.router.navigate(['getmyrecipes']);
  }
  viewProducts(): any {
    this.router.navigate(['viewproducts']);
  }
  viewcart(): any {
    this.router.navigate(['viewcart']);
  }
  getmyorders(): any {
    this.router.navigate(['vieworders']);
  }
  getRqsts() {
    this.router.navigate(['showRqsts']);
  }

}
