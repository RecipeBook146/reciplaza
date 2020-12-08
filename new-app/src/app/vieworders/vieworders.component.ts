import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';

@Component({
  selector: 'app-vieworders',
  templateUrl: './vieworders.component.html',
  styleUrls: ['./vieworders.component.css']
})
export class ViewordersComponent implements OnInit {

  prodList: any;
  orders: any;
  loginId: any;
  totalAmt: number
  dc: number;
  ndc: number;
  cc: number;

  constructor(private service: UserserviceService) {
    this.loginId = service.getLoggedId();
    this.totalAmt = 0;
    this.dc = 0;
    this.ndc = 0;
    this.cc = 0;
  }

  ngOnInit(): void {

    this.service.getAllProd(this.loginId).subscribe((result: any) => { console.log(result); this.prodList = result; });
    this.service.getOrders(this.loginId).subscribe((result: any) => {
      console.log(result);
      this.orders = result;
      this.dc = 0;
      this.ndc = 0;
      this.cc = 0;
      for (var i = 0; i < this.orders.length; i++) {
        if (this.orders[i].status === 'Delivered') {
          this.dc += 1;
        } else if (this.orders[i].status === 'Not Delivered') {
          this.ndc += 1;
        } else {
          this.cc += 1;
        }
      }
      console.log(this.dc);
      console.log(this.ndc);
      console.log(this.cc);

    });
  }

  deleteOrder(orderId: any) {
    this.service.deleteOrder(orderId).subscribe((result: any) => { console.log(result); });
    this.dc = 0;
    this.ndc = 0;
    this.cc = 0;
    this.ngOnInit();
    this.dc = 0;
    this.ndc = 0;
    this.cc = 0;
    this.ngOnInit();
  }
}
