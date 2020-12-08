import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-myrecipes',
  templateUrl: './myrecipes.component.html',
  styleUrls: ['./myrecipes.component.css']
})
export class MyrecipesComponent implements OnInit {
  id:any;
  recipes:any;
  recipe:any;
  ings:any;
  steps:any;

  constructor(private service:UserserviceService,private router:Router) { }

  ngOnInit(): void {
    this.id=this.service.getLoggedId();
    this.service.getUploadedRecipes(this.id).subscribe((result:any)=>{console.log(result);this.recipes=result;});
  }
  fullRecipe(recipe:any){
    this.service.setRecipe(recipe);
    localStorage.setItem('msg', JSON.stringify('msg'));
    this.router.navigate(['viewrecipe']);
  }
  uploadRecipe():any{
    this.router.navigate(['addRecipe']);
  }

}
