import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormArray, Validators, FormBuilder } from '@angular/forms';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { noUndefined } from '@angular/compiler/src/util';
declare var jQuery: any;


@Component({
  selector: 'app-addrecipe',
  templateUrl: './addrecipe.component.html',
  styleUrls: ['./addrecipe.component.css']
})
export class AddrecipeComponent implements OnInit {
  demoForm: FormGroup;
  recipe: any;
  imageUrl: string;
  fileToUpload: File = null;
  reader: FileReader;

  constructor(private router: Router, private _fb: FormBuilder, private service: UserserviceService, private toastr: ToastrService) {
    this.recipe = { recipeId: 1, title: '', category: '', cookTime: '', persons: '', description: '', ingredients: '', proc: '', tips: '', likes: 0, rating: 0, stat: '', price: '', np: '', conf: '', user: { loginId: '', email: '', password: '', imageName: '' } };
    this.recipe.user.loginId = service.getLoggedId();
    this.createForm();
    this.imageUrl = '';
  }
  ngOnInit() {
    this.demoForm = this._fb.group({
      title: '',
      category: '',
      cookTime: '',
      persons: '',
      description: '',
      itemRows: this._fb.array([this.initItemRows()]),
      steps: this._fb.array([this.initSteps()]),
      tips: ''
    });
    this.imageUrl = '';
  }
  get formArr() {
    return this.demoForm.get('itemRows') as FormArray;
  }
  get newArr() {
    return this.demoForm.get('steps') as FormArray;
  }
  initSteps() {
    return this._fb.group({
      step: ['']
    });
  }
  initItemRows() {
    return this._fb.group({
      ingName: [''],
      quantity: ['']
    });
  }
  createForm() {
    this.demoForm = this._fb.group({
      itemRows: this._fb.array([]),
      steps: this._fb.array([])
    });
    this.demoForm.setControl('itemRows', this._fb.array([]));
    this.demoForm.setControl('steps', this._fb.array([]));
  }

  get itemRows(): FormArray {
    return this.demoForm.get('itemRows') as FormArray;
  }

  get steps(): FormArray {
    return this.demoForm.get('steps') as FormArray;
  }

  addNewRow() {
    this.formArr.push(this.initItemRows());
  }

  deleteRow(index: number) {
    this.formArr.removeAt(index);
  }

  addNewRow2() {
    this.newArr.push(this.initSteps());
  }

  deleteRow2(index: number) {
    this.newArr.removeAt(index);
  }
  onSubmit() {
    console.log(this.demoForm.value);
    this.recipe.title = this.demoForm.value.title;
    this.recipe.category = this.demoForm.value.category;
    this.recipe.cookTime = this.demoForm.value.cookTime;
    this.recipe.persons = this.demoForm.value.persons;
    this.recipe.description = this.demoForm.value.description;
    this.recipe.tips = this.demoForm.value.tips;
    this.recipe.stat = "no";
    for (var i = 0; i < this.demoForm.value.itemRows.length; i++) {
      this.recipe.ingredients += this.demoForm.value.itemRows[i].ingName + " " + this.demoForm.value.itemRows[i].quantity + "@"
    }
    for (var i = 0; i < this.demoForm.value.steps.length; i++) {
      this.recipe.proc += this.demoForm.value.steps[i].step + "@"
    }
    console.log(this.recipe);
    if (confirm("Want to Cook this recipe!")) {
      this.recipe.stat = "yes";
      jQuery('#cookModal').modal('show');
    } else {
      console.log(this.recipe);
      this.service.addRecipe(this.recipe, this.fileToUpload).subscribe((result: any) => { console.log(this.recipe); this.toastr.success("Added Recipe!"); });
    }
  }
  Cancel() {
    jQuery('#cookModal').modal('hide');
    console.log(this.recipe);
    this.recipe.stat = "no";
    this.service.addRecipe(this.recipe, this.fileToUpload).subscribe((result: any) => { console.log(this.recipe); this.toastr.success("Added Recipe!"); });
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
  OnSubmit(imageForm: any) {
    console.log(imageForm);
    this.service.postFile(imageForm, this.fileToUpload).subscribe(
      data => {
        console.log('done');
        this.imageUrl = '';
      }
    );
  }
  demo() {
    this.recipe.stat = "yes";
    console.log(this.recipe);
    jQuery('#cookModal').modal('hide');
    this.service.addRecipe(this.recipe, this.fileToUpload).subscribe((result: any) => { console.log(this.recipe); this.toastr.success("Added Recipe!"); });
  }
}



