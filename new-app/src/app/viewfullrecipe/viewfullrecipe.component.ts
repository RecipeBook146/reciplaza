import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-viewfullrecipe',
  templateUrl: './viewfullrecipe.component.html',
  styleUrls: ['./viewfullrecipe.component.css']
})
export class ViewfullrecipeComponent implements OnInit {
  recipe:any;
  ings:any;
  steps:any;
  id:any;
  nums=[0,1,2,3,4];
  clicked=false;
  starList: boolean[] = [true, true, true, true, true];
  rating: number;
  count:any;
  ratingObj:any;
  msg:any;
  stat:any;

  constructor(private service:UserserviceService,private router:Router) {
    this.ratingObj={id:1,rating:'',user:{loginId:'',email:'',password:''},recipe:{recipeId:''}};
   }

  ngOnInit(): void {
    this.msg = JSON.parse(localStorage.getItem("msg"));
    localStorage.setItem('msg', JSON.stringify(''));
    this.id=this.service.getLoggedId();
    this.recipe=this.service.getRecipe();
    this.service.getStatus(this.recipe.recipeId,this.id).subscribe((result:any)=>{console.log(result);this.stat=result;});
    this.count=this.recipe.likes;
    this.ings=this.recipe.ingredients.split('@');
    this.ings=this.ings.splice(0,this.ings.length-1);
    this.steps=this.recipe.proc.split('@');
    this.steps=this.steps.splice(0,this.steps.length-1);
    this.ratingObj.user.loginId=this.id;
    this.ratingObj.recipe.recipeId=this.recipe.recipeId;
  }

  method(ing:any):any{
    this.service.setProduct(ing);
    
    this.router.navigate(['viewproducts']);
  }
  change(){
    this.clicked=true;
  }
  setStar(data: any) {
    this.rating = data + 1;
    for (var i = 0; i <= 4; i++) {
      if (i <= data) {
        this.starList[i] = false;
      }
      else {
        this.starList[i] = true;
      }
    }
    this.recipe.rating=this.rating;
    this.count+=1;
    this.ratingObj.rating=this.rating;
    this.service.addToRating(this.ratingObj).subscribe((result:any)=>{console.log(result);});
    //this.ngOnInit();
    //this.ngOnInit();
  }
  

}
