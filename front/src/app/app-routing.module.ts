import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductsComponent }  from './products.component';
import { ShoppingCartComponent }  from './shopping-cart.component';
import { PageNotFoundComponent }    from './page-not-found.component';

const appRoutes: Routes = [
	{ path: '', redirectTo: '/products', pathMatch: 'full' },
	{ path: 'products', component: ProductsComponent },
	{ path: 'shoppingcart', component: ShoppingCartComponent },
	{ path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }