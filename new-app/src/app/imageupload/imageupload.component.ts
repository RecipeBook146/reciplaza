import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';

@Component({
  selector: 'app-imageupload',
  templateUrl: './imageupload.component.html',
  styleUrls: ['./imageupload.component.css']
})
export class ImageuploadComponent implements OnInit {

  imageUrl:string;
  fileToUpload:File=null;
  reader:FileReader;

  constructor(private service:UserserviceService) {
    this.imageUrl='/assets/Images/recipe.jpg';
   }

  ngOnInit(): void {
  }
  handleFileInput(file:FileList){
    this.fileToUpload=file.item(0);
    this.reader=new FileReader();
    this.reader.readAsDataURL(this.fileToUpload);
    this.reader.onload=(event:any)=>{
      this.imageUrl=event.target.result;
      console.log(this.imageUrl);
    };
  }
  OnSubmit(imageForm:any){
    console.log(imageForm);
    this.service.postFile(imageForm,this.fileToUpload).subscribe(  
      data=>{
        console.log('done');
        this.imageUrl='/assets/Images/recipe.jpg';
      }
    );
  }


}
