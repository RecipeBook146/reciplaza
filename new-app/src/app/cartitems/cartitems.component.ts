import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';
declare var jQuery: any;

@Component({
  selector: 'app-cartitems',
  templateUrl: './cartitems.component.html',
  styleUrls: ['./cartitems.component.css']
})
export class CartitemsComponent implements OnInit {
  products: any;
  loginId: any;
  count: any;
  address: any;
  chosenId: any;
  totalAmt: number;
  show: any;

  constructor(private service: UserserviceService, private router: Router) {
    this.loginId = service.getLoggedId();
    this.totalAmt = 0;
  }

  ngOnInit(): void {
    this.service.getCartProducts(this.loginId).subscribe((result: any) => { console.log(result); this.products = result; });
    this.service.getQuan(this.loginId).subscribe((result: any) => { console.log(result); this.count = result; });
    this.service.getAddress(this.loginId).subscribe((result: any) => { console.log(result); this.address = result; });
    //this.service.getTot(this.loginId).subscribe((result:any)=>{console.log(result);this.totalAmt=result;});
    this.show = localStorage.getItem("show");
    if (this.show) {
      localStorage.setItem("show", "");
      jQuery('#chooseAddressModal').modal('show');
    }
  }

  iupdate(productId: any, c: any) {
    c += 1;
    console.log(c);
    this.service.updateCart(c, this.loginId, productId).subscribe((result: any) => { console.log(result); });
    this.ngOnInit();
  }

  dupdate(productId: any, c: any) {
    c -= 1;
    console.log(c);
    this.service.updateCart(c, this.loginId, productId).subscribe((result: any) => { console.log(result); });
    this.ngOnInit();
  }
  delete(product: any, c: any) {
    this.service.deleteItem(this.loginId, product.productId).subscribe((result: any) => {
      const i = this.products.findIndex((element) => {
        return product.productId === element.productId;
      });
      this.products.splice(i, 1);
    });
    this.totalAmt -= product.price * c;
  }

  showEditPopup() {
    if (this.address.length > 0) {
      jQuery('#chooseAddressModal').modal('show');
    } else {
      jQuery('#noAddressModal').modal('show');
    }
  }

  addAddress() {
    jQuery('#noAddressModal').modal('hide');
    jQuery('#chooseAddressModal').modal('hide');
    localStorage.setItem("show1", "show1");
    this.router.navigate(['address']);
  }
  placeOrder() {
    localStorage.setItem('chosenId', JSON.stringify(this.chosenId));
    localStorage.setItem('products', JSON.stringify(this.products));
    localStorage.setItem('count', JSON.stringify(this.count));
    console.log(this.chosenId);
    jQuery('#chooseAddressModal').modal('hide');
    this.router.navigate(['orders']);
  }


}