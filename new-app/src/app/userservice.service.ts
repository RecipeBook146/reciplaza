import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {

  private isUserLogged: boolean = false;
  private isAdminLogged: boolean = false;

  private stat: any;
  private demo: any;
  private loggedId: any = "";

  private products: any;
  private recipe: any;


  private cartProd: Subject<any>;

  constructor(private httpClient: HttpClient) {
    this.isUserLogged = false;
    this.cartProd = new Subject();
    this.stat = true;
  }

  setProduct(demo: any) {
    this.products = demo;
  }

  getRecipe(): any {
    return this.recipe;
  }

  setRecipe(demo: any) {
    this.recipe = demo;
  }

  getProduct(): any {
    return this.products;
  }

  setLoggedId(id: any): void {
    this.loggedId = id;
  }

  getLoggedId(): any {
    return this.loggedId;
  }

  setStat(stat: any): void {
    this.stat = stat;
  }
  getStat(): any {
    return this.stat;
  }
  setUserLoggedIn(): void {
    this.isUserLogged = true;
  }
  setUserLoggedOut(): void {
    this.isUserLogged = false;
    this.loggedId = "";
  }
  getUserLogged(): any {
    return this.isUserLogged;
  }
  setAdminLoggedIn(): void {
    this.isAdminLogged = true;
  }
  setAdminLoggedOut(): void {
    this.isAdminLogged = false;
  }
  getAdminLogged(): any {
    return this.isAdminLogged;
  }
  registerUser(user: any) {
    return this.httpClient.post('RESTAPI2018/webapi/myresource/registerUser', user);
  }

  signInWithGoogle(user: any) {
    return this.httpClient.post('RESTAPI2018/webapi/myresource/signInWithGoogle', user);
  }

  addRqst(rqstRec:any){
    return this.httpClient.post('RESTAPI2018/webapi/myresource/addRqst',rqstRec);
  }
  

  verifyLogin(loginId: any): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/verifyLogin/' + loginId);
  }
  addProduct(product: any, fileToUpload: File) {
    const formData: FormData = new FormData();
    formData.append('Image', fileToUpload, fileToUpload.name);
    formData.append('produtName', product.produtName);
    formData.append('description', product.description);
    formData.append('quantity', product.quantity);
    formData.append('price', product.price);
    formData.append('manufacturer', product.manufacturer);
    return this.httpClient.post('RESTAPI2018/webapi/myresource/addProduct', formData);
  }

  getStatus(recipeId: any, loginId: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getStat/' + recipeId + '/' + loginId);
  }

  addRecipe(recipe: any, fileToUpload: File) {
    const formData: FormData = new FormData();
    formData.append('Image', fileToUpload, fileToUpload.name);
    formData.append('title', recipe.title);
    formData.append('category', recipe.category);
    formData.append('cookTime', recipe.cookTime);
    formData.append('persons', recipe.persons);
    formData.append('description', recipe.description);
    formData.append('ingredients', recipe.ingredients);
    formData.append('proc', recipe.proc);
    formData.append('tips', recipe.tips);
    formData.append('stat',recipe.stat);
    formData.append('price',recipe.price);
    formData.append('np',recipe.np);
    formData.append('conf',recipe.conf);
    formData.append('loginId', recipe.user.loginId);
    return this.httpClient.post('RESTAPI2018/webapi/myresource/addRecipe', formData);
  }
  allRecipe(): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/allRecipe');
  }

  getAllUsers(): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getAllUsers');
  }

  addToFav(fav: any) {
    return this.httpClient.post('RESTAPI2018/webapi/myresource/addToFav', fav);
  }
  getFavRecipes(loginId: any): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getFavRecipes/' + loginId);
  }
  getUploadedRecipes(loginId: any): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getUploadedRecipes/' + loginId);
  }
  getProducts(desc: any): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getProducts/' + desc);
  }

  getAllProducts(): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getAllProducts');
  }

  getCount(recipeId: any): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getCount/' + recipeId);
  }

  addToRating(rating: any) {
    return this.httpClient.post('RESTAPI2018/webapi/myresource/addToRating', rating);
  }

  postFile(ImageForm: any, fileToUpload: File) {
    //const endpoint='RESTAPI/webapi/myresource/';
    const formData: FormData = new FormData();
    formData.append('Image', fileToUpload, fileToUpload.name);
    formData.append('demo', ImageForm.demo);
    return this.httpClient.post('RESTAPI2018/webapi/myresource/uploadImage', formData);
  }

  addToCart(cart: any) {
    return this.httpClient.post('RESTAPI2018/webapi/myresource/addToCart', cart);
  }

  getCartProducts(loginId: any): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getCartProducts/' + loginId);
  }

  getAllProd(loginId: any): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getAllProd/' + loginId);
  }

  getOrders(loginId: any): any {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getOrders/' + loginId);
  }

  setForCart(product: any) {
    this.cartProd.next(product);
  }

  getForCart() {
    return this.cartProd.asObservable();
  }
  deleteItem(loginId: any, productId: any) {
    return this.httpClient.delete('RESTAPI2018/webapi/myresource/deleteItem/' + loginId + '/' + productId);
  }

  deleteFav(recipeId: any, loginId: any) {
    return this.httpClient.delete('RESTAPI2018/webapi/myresource/deleteFav/' + recipeId + '/' + loginId);
  }
  getRqsts(loginId: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getRqsts/' + loginId);
  }
  getAsks(loginId: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getAsks/' + loginId);
  }
  acceptRqst(rqst:any){
    return this.httpClient.put('RESTAPI2018/webapi/myresource/acceptRqst' , rqst);
  }
  rejectRqst(rqst:any){
    return this.httpClient.put('RESTAPI2018/webapi/myresource/rejectRqst' , rqst);
  }
  updateAsk(ask:any){
    return this.httpClient.put('RESTAPI2018/webapi/myresource/updateAsk' , ask);
  }
  deleteAsk(ask:any){
    console.log(ask);
    return this.httpClient.post('RESTAPI2018/webapi/myresource/deleteAsk' , ask);
  }

  getQuan(loginId: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getQuan/' + loginId);
  }
  getTot(loginId: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getTot/' + loginId);
  }
  updateCart(quan: any, loginId: any, productId: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/updateCart/' + quan + '/' + loginId + '/' + productId);
  }
  getAddress(loginId: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getAddress/' + loginId);
  }
  addAddress(address: any) {
    return this.httpClient.post('RESTAPI2018/webapi/myresource/addAddress', address);
  }
  deleteAddress(address: any) {
    return this.httpClient.delete('RESTAPI2018/webapi/myresource/deleteAddress/' + address.id);
  }

  deleteOrder(orderId: any) {
    return this.httpClient.delete('RESTAPI2018/webapi/myresource/deleteOrder/' + orderId);
  }

  updateAddress(address: any) {
    return this.httpClient.put('RESTAPI2018/webapi/myresource/updateAddress', address);
  }
  getAddressById(chosenId: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/getAddressById/' + chosenId);
  }

  addOrder(loginId: any, id: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/addOrder/' + loginId + '/' + id);
  }

  sendMail(email: any, loginId: any) {
    return this.httpClient.get('RESTAPI2018/webapi/myresource/sendMail/' + email + '/' + loginId);
  }

  deleteProduct(product: any) {
    console.log(product);
    return this.httpClient.delete('RESTAPI2018/webapi/myresource/deleteProduct/' + product.productId);
  }
  updateProduct(editProduct: any) {
    return this.httpClient.put('RESTAPI2018/webapi/myresource/updateProduct', editProduct);
  }
}
