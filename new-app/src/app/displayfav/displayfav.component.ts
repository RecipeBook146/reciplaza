import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-displayfav',
  templateUrl: './displayfav.component.html',
  styleUrls: ['./displayfav.component.css']
})
export class DisplayfavComponent implements OnInit {
  favs: any;
  id: any;
  recipes: any;
  recipe: any;
  ings: any;
  steps: any;

  constructor(private service: UserserviceService, private router: Router) { }

  ngOnInit(): void {
    this.id = this.service.getLoggedId();
    this.service.allRecipe().subscribe((result: any) => { console.log(result); this.recipes = result; });
    this.service.getFavRecipes(this.id).subscribe((result: any) => { console.log(result); this.favs = result; });

  }
  fullRecipe(recipe: any) {
    this.service.setRecipe(recipe);
    this.router.navigate(['viewrecipe']);
  }
  viewRecipes() {
    this.router.navigate(["allRecipe"]);
  }
  deleteFav(recipe: any) {
    this.service.deleteFav(recipe.recipeId, this.id).subscribe((result: any) => {
      const i = this.favs.findIndex((element) => {
        return recipe.recipeId === element.recipeId;
      });
      this.favs.splice(i, 1);
    });

  }

}
