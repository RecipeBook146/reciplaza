import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  products:any;
  address:any;
  count:any;
  totalAmt:any;
  loginId:any;
  chosenId:any;
  constructor(private service:UserserviceService,private toastr: ToastrService) {
    this.loginId=service.getLoggedId();
    this.totalAmt=0;
   }

  ngOnInit(): void {
    this.products=JSON.parse(localStorage.getItem("products"));
    this.chosenId=JSON.parse(localStorage.getItem('chosenId'));
    this.count=JSON.parse(localStorage.getItem('count'));
    this.service.getAddressById(this.chosenId).subscribe((result: any) => { console.log(result); this.address = result; });
    console.log(this.address);
  }

  addOrder(){
    this.service.addOrder(this.loginId,this.chosenId).subscribe((result:any)=>{console.log(result);});
    this.toastr.success('Order Placed!!');
  }

}
