import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-addproducts',
  templateUrl: './addproducts.component.html',
  styleUrls: ['./addproducts.component.css']
})
export class AddproductsComponent implements OnInit {
  product: any;
  imageUrl: string;
  fileToUpload: File = null;
  reader: FileReader;

  constructor(private service: UserserviceService, private toastr: ToastrService) {
    this.product = { productId: 1, produtName: '', description: '', quantity: '', price: '', manufacturer: '' };
  }

  ngOnInit(): void {
  }

  add(addProd: any) {
    this.toastr.warning("Please Fill All Details!!");
    this.service.addProduct(this.product, this.fileToUpload).subscribe((result: any) => { console.log(this.product); this.toastr.success("Product Added!!"); });

  }
  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0);
    this.reader = new FileReader();
    this.reader.readAsDataURL(this.fileToUpload);
    this.reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
      console.log(this.imageUrl);
    };

  }
}