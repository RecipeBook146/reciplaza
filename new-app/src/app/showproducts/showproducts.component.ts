import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
declare var jQuery:any;

@Component({
  selector: 'app-showproducts',
  templateUrl: './showproducts.component.html',
  styleUrls: ['./showproducts.component.css']
})
export class ShowproductsComponent implements OnInit {
  products:any;
  editProduct:any;

  constructor(private service: UserserviceService) { 
    this.editProduct={productId: '', produtName: '', description: '', quantity: '', price: '', manufacturer: '' };
  }

  ngOnInit(): void {
    this.service.getAllProducts().subscribe((result: any) => {
      console.log(result);
      this.products = result;
    });
  }

  showUpdatePopup(product:any){
    this.editProduct=product;
    jQuery('#editProductModal').modal('show');
  }
  updateProduct(){
    jQuery('#editProductModal').modal('hide');
    console.log(this.editProduct);
    this.service.updateProduct(this.editProduct).subscribe((result:any)=>{console.log(result);});
  }
  deleteProduct(product:any){
    this.service.deleteProduct(product).subscribe((result:any)=>{
      const i=this.products.findIndex((element)=>{return product.empId===element.empId;
      });
      this.products.splice(i,1);
    });
    console.log(product);
  }

}
