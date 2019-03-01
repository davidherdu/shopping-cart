import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { Router } from '@angular/router';

import { ProductReactiveService } from './product-reactive.service';
import { ProductsComponent } from './products.component';
import { ProductsQuantityComponent } from './products-quantity.component';
import { ShoppingCartReactiveService } from './shopping-cart-reactive.service';
import { AppComponent } from './app.component';
import { ShoppingCartComponent } from './shopping-cart.component';
import { AppRoutingModule } from './app-routing.module';
import { PageNotFoundComponent }    from './page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
	ProductsQuantityComponent,
	ShoppingCartComponent,
	PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
	AppRoutingModule
  ],
  providers: [
    ProductReactiveService,
	ShoppingCartReactiveService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
	constructor(router: Router) {}
}
