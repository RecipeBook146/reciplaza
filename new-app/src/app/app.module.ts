import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { AddproductsComponent } from './addproducts/addproducts.component';
import { AllrecipeComponent } from './allrecipe/allrecipe.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AddrecipeComponent } from './addrecipe/addrecipe.component';
import { DisplayfavComponent } from './displayfav/displayfav.component';
import { UserheaderComponent } from './userheader/userheader.component';
import { MyrecipesComponent } from './myrecipes/myrecipes.component';
import { ViewproductsComponent } from './viewproducts/viewproducts.component';
import { ViewfullrecipeComponent } from './viewfullrecipe/viewfullrecipe.component';
import { ImageuploadComponent } from './imageupload/imageupload.component';
import { ProductsheaderComponent } from './productsheader/productsheader.component';
import { CartitemsComponent } from './cartitems/cartitems.component';
import { AddressComponent } from './address/address.component';
import { OrdersComponent } from './orders/orders.component';
import { PricepipePipe } from './pricepipe.pipe';
import { DemopipePipe } from './demopipe.pipe';
import { ViewordersComponent } from './vieworders/vieworders.component';
import { ShowproductsComponent } from './showproducts/showproducts.component';
import { FilterPipePipe } from './filter-pipe.pipe';
import { FilterUserPipe } from './filter-user.pipe';
import { SocialLoginModule, SocialAuthServiceConfig } from 'angularx-social-login';
import { GoogleLoginProvider } from 'angularx-social-login';
import { ShowRqstsComponent } from './show-rqsts/show-rqsts.component';
import { SepPipePipe } from './sep-pipe.pipe';


const appRoot: Routes = [{ path: '', component: AllrecipeComponent },
{ path: 'login', component: LoginComponent },
{ path: 'register', component: RegisterComponent },
{ path: 'addProd', component: AddproductsComponent },
{ path: 'allRecipe', component: AllrecipeComponent },
{ path: 'addRecipe', component: AddrecipeComponent },
{ path: 'getfav', component: DisplayfavComponent },
{ path: 'getmyrecipes', component: MyrecipesComponent },
{ path: 'viewproducts', component: ViewproductsComponent },
{ path: 'viewrecipe', component: ViewfullrecipeComponent },
{ path: 'viewcart', component: CartitemsComponent },
{ path: 'address', component: AddressComponent },
{ path: 'orders', component: OrdersComponent },
{ path: 'vieworders', component: ViewordersComponent },
{ path: 'showProducts', component: ShowproductsComponent },
{ path: 'showRqsts', component: ShowRqstsComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    AddproductsComponent,
    AddrecipeComponent,
    AllrecipeComponent,
    DisplayfavComponent,
    UserheaderComponent,
    MyrecipesComponent,
    ViewproductsComponent,
    ViewfullrecipeComponent,
    ImageuploadComponent,
    ProductsheaderComponent,
    CartitemsComponent,
    AddressComponent,
    OrdersComponent,
    PricepipePipe,
    DemopipePipe,
    ViewordersComponent,
    ShowproductsComponent,
    FilterPipePipe,
    FilterUserPipe,
    ShowRqstsComponent,
    SepPipePipe
  ],
  imports: [
    BrowserModule, FormsModule, SocialLoginModule, HttpClientModule, RouterModule.forRoot(appRoot), ReactiveFormsModule, BrowserAnimationsModule,
    ToastrModule.forRoot()

  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '255583398826-j9vsq8thqoi08mkoe3idt57k43radn7f.apps.googleusercontent.com'
            ),
          },
        ],
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
