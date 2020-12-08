import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
declare var jQuery: any;

@Component({
  selector: 'app-show-rqsts',
  templateUrl: './show-rqsts.component.html',
  styleUrls: ['./show-rqsts.component.css']
})
export class ShowRqstsComponent implements OnInit {
  rqsts:any;
  asks:any;
  loginId:any;
  todate: any;
  rqstRec:any;

  constructor(private service:UserserviceService) {
    this.rqstRec = { id: 1, persons: '', date: '', time: '', address: '', message: '', status:'', user:{loginId: ''}, user1:{loginId: ''}, recipe:{recipeId: ''}};
    this.todate = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
    this.loginId=service.getLoggedId();
   }

  ngOnInit(): void {
    this.service.getAsks(this.loginId).subscribe((result:any)=>{this.asks=result;console.log(result);});
    this.service.getRqsts(this.loginId).subscribe((result:any)=>{this.rqsts=result;console.log(result);});
  }
  accept(rqst:any){
    rqst.status="a";
    this.service.acceptRqst(rqst).subscribe((result:any)=>{console.log("Accepted");});
  }
  reject(rqst:any){
    rqst.status="r";
    this.service.rejectRqst(rqst).subscribe((result:any)=>{console.log("Rejected");});
  }
  delete(ask:any){
    console.log(ask);
    this.service.deleteAsk(ask).subscribe((result: any) => {
      const i = this.asks.findIndex((element) => {
        return ask.Id === element.Id;
      });
      this.asks.splice(i, 1);
    });
  }
  edit(ask:any){
    this.rqstRec=ask;
    jQuery('#cookModal').modal('show');
  }
  update(){
    jQuery('#cookModal').modal('hide');
    this.service.updateAsk(this.rqstRec).subscribe((result:any)=>{console.log("Upadted");});
  }
  cancel(){
    jQuery('#cookModal').modal('hide');
  }
}
