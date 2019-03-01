import { Component, OnInit } from '@angular/core';

import { Product } from './product';
import { Cart } from './cart';
import { CartItem } from './cartItem';
import { ShoppingCartReactiveService } from './shopping-cart-reactive.service';

import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-component-shopping-cart',
  providers: [ShoppingCartReactiveService],
  templateUrl: './shopping-cart.component.html'
})
export class ShoppingCartComponent implements OnInit {
	cart$: Observable<Cart>;
	mode: String;
	disableDelete: Boolean = localStorage.getItem('cartId') === null;

	constructor(private shoppingCartReactiveService: ShoppingCartReactiveService) {
		this.mode = "reactive";
	}
	
	ngOnInit(): void {
		let cartId = parseInt(localStorage.getItem('cartId'));
		this.cart$ = this.shoppingCartReactiveService.getShoppingCartStream(cartId);
    }
	
	deleteShoppingCart(): void {
		let cartId = parseInt(localStorage.getItem('cartId'));
		this.shoppingCartReactiveService.deleteShoppingCartStream(cartId).subscribe();
		localStorage.removeItem('cartId');
	}
}
