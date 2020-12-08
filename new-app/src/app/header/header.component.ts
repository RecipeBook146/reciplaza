import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  /*paths: any;
  names: any;
  status:boolean=false;
  userStatus: boolean=false;
  adminStatus: boolean=false;*/
  constructor(private service: UserserviceService,private router:Router) {
    
  }

  ngOnInit(): void {
    /*if (this.service.getUserLogged()) {
      this.paths=["login","register"];
      this.names=["demo1","demo2"];
      this.userStatus=this.service.getUserLogged();
    } else if (this.service.getAdminLogged()) {
      this.paths=["login","register"];
      this.names=["demo1","demo2"];
      this.userStatus=this.service.getAdminLogged();
    } else {
      this.paths = ["login", "register"];
      this.names = ["Login", "Register"];
      this.status = !this.service.getUserLogged();
    }*/
  }
  login():any{
    this.router.navigate(['login']);
  }
  register():any{
    this.router.navigate(['register']);
  }

}
