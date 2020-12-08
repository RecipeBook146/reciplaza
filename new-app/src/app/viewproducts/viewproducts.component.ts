import { Component, OnInit } from '@angular/core';
import { AllrecipeComponent } from '../allrecipe/allrecipe.component';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-viewproducts',
  templateUrl: './viewproducts.component.html',
  styleUrls: ['./viewproducts.component.css']
})
export class ViewproductsComponent implements OnInit {
  products: any;
  ing: any;
  cart:any;
  loggedId:any;
  searchProd:any;
  stat:any;
  message:any;

  constructor(private service: UserserviceService, private router: Router,private toastr: ToastrService) { 
    this.loggedId=service.getLoggedId();
    this.cart={id:1,quantity:1,price:'',user:{loginId:''},product:{productId:''}};
    this.searchProd="";
  }

  ngOnInit(): void {
    this.stat=this.service.getStat();
    this.service.setStat(true);
    this.ing = this.service.getProduct();
    console.log(this.ing);
    if (this.ing) {
      this.service.getProducts(this.ing).subscribe((result: any) => {
        console.log(result);
        this.products = result;
      });
      console.log(this.products);
      this.service.setProduct("");
    }else{
      this.service.getAllProducts().subscribe((result: any) => {
        console.log(result);
        this.products = result;
      });
      //console.log(this.products);
    }
  }
  navigate(): any {
    this.router.navigate(['viewrecipe']);
  }

  addToCart(product:any){
    this.cart.price=product.price;
    this.cart.product.productId=product.productId;   
    this.cart.user.loginId=this.loggedId;
    this.service.addToCart(this.cart).subscribe((result:any)=>{console.log(this.cart);
    this.message=result;
    if(this.message.message==="inserted"){
      this.service.setForCart(product);
      this.toastr.success('Added to cart.');
    }else{
      this.toastr.warning('Product Already exists in Cart.Updated Quantity!!')
    }
    });
    
  }

  searchProds(){
    this.service.setProduct(this.searchProd);
    this.service.setStat(false);
    this.ngOnInit();
  }

}
