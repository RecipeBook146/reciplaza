import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-productsheader',
  templateUrl: './productsheader.component.html',
  styleUrls: ['./productsheader.component.css']
})
export class ProductsheaderComponent implements OnInit {

  constructor(private service:UserserviceService,private router:Router) { }

  ngOnInit(): void {
  }
  logout():any{
    this.service.setAdminLoggedOut();
    this.router.navigate(['']);
  }
  showProducts(){
    this.router.navigate(['showProducts']);
  }
  addProd(){
    this.router.navigate(['addProd']);
  }
}
