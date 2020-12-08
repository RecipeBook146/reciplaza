import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';
declare var jQuery: any;

@Component({
  selector: 'app-allrecipe',
  templateUrl: './allrecipe.component.html',
  styleUrls: ['./allrecipe.component.css']
})
export class AllrecipeComponent implements OnInit {
  recipes: any;
  favs: any;
  recipe: any;
  id: any;
  ings: any;
  steps: any;
  vals: any;
  products: any;
  nums = [0, 1, 2, 3, 4];
  searchRec: any;
  cookRec: any;
  rqstRec: any;
  todate: any;



  /*val:number=3;
  starList: boolean[] = [true, true, true, true, true];
  rating: number;*/
  constructor(private router: Router, private service: UserserviceService) {
    this.todate = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
    this.rqstRec = { id: 1, persons: '', date: '', time: '', address: '', message: '', status:'', user:{loginId: ''}, user1:{loginId: ''}, recipe:{recipeId: ''} };
    this.cookRec = { recipeId: 1, title: '', category: '', cookTime: '', persons: '', description: '', ingredients: '', proc: '', tips: '', likes: 0, rating: 0, stat: '', price: '', np: '', conf: '', user: { loginId: '', email: '', password: '', imageName: '' } };
    /*this.recipe={recipeId:1,title:'',ingredients:'',proc:'',likes:0,rating:0,user:{loginId:'',email:'',password:''}};
    this.recipe.user.loginId=service.getLoggedId(); */
    this.searchRec = "";
  }

  ngOnInit() {
    this.id = this.service.getLoggedId();
    /*for (var i = 0; i <= 4; i++) {
      if (i < this.val) {
        this.starList[i] = false;
      }
      else {
        this.starList[i] = true;
      }
    }*/
    this.service.allRecipe().subscribe((result: any) => { console.log(result); this.recipes = result; });
    this.service.getFavRecipes(this.id).subscribe((result: any) => { console.log(result); this.favs = result; });
  }

  fullRecipe(recipe: any) {
    /*this.recipe=recipe;
    this.ings=this.recipe.ingredients.split('@');
    this.ings=this.ings.splice(0,this.ings.length-1);
    this.steps=this.recipe.proc.split('@');
    this.steps=this.steps.splice(0,this.steps.length-1);*/
    this.service.setRecipe(recipe);
    this.router.navigate(['viewrecipe']);
  }

  addRec(): any {
    this.router.navigate(['addRecipe']);
  }
  fav(recipeId: any) {
    var fav = { id: 1, user: { loginId: this.id }, recipe: { recipeId: recipeId } };
    this.service.addToFav(fav).subscribe((result: any) => { console.log(fav); });
    this.ngOnInit();
    this.ngOnInit();
  }
  show(recipe: any) {
    this.cookRec = recipe;
    jQuery('#cookModal').modal('show');
  }
  Cancel() {
    jQuery('#cookModal').modal('hide');
  }
  submit(loginId: any, recipeId: any) {
    jQuery('#cookModal').modal('hide');
    this.rqstRec.status="p";
    this.rqstRec.user.loginId = this.id;
    this.rqstRec.user1.loginId = loginId;
    this.rqstRec.recipe.recipeId = recipeId;
    var [h, m] = this.rqstRec.time.split(":");
    this.rqstRec.time = h+":"+m+":"+0;
    //console.log((h%12)+":"+m, h >= 12 ? 'PM' : 'AM');
    console.log(this.rqstRec);
    this.service.addRqst(this.rqstRec).subscribe((result:any)=>{console.log(this.rqstRec);});
  }

}