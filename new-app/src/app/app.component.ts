import { Component } from '@angular/core';
import { UserserviceService } from './userservice.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  //status:any=false;

  constructor(private service:UserserviceService){
    //this.status=service.getUserLogged();
  }
}
