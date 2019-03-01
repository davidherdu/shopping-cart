import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

import { Product } from './product';
import { Cart } from './cart';
import { ShoppingCartReactiveService } from './shopping-cart-reactive.service';

import { Observable } from 'rxjs/Observable';
import { map, mergeMap } from 'rxjs/operators';

@Component({
  selector: 'products-quantity-select',
  providers: [ShoppingCartReactiveService],
  templateUrl: './products-quantity-select.html',
})
export class ProductsQuantityComponent implements OnChanges {
	@Input() product: Product;
	selectedQuantity: number;
	items: number[];
	
	constructor(private shoppingCartReactiveService: ShoppingCartReactiveService) {
		this.items = this.initArray();
	}
	
	initArray(): number[] {
		let quantityMax: number = 5;
		let numberArray: number[] = [];
		for (let _i = 1; _i <= quantityMax; _i++) {
			numberArray[_i] = _i;
		}
		return numberArray;
	}
	
	ngOnChanges(changes: SimpleChanges): void {
		this.selectedQuantity = 1;
	}
	
	addToCart(): void {
		let cartLocalStorage = localStorage.getItem('cartId');
		let cartId = cartLocalStorage === null ? -1 : parseInt(cartLocalStorage);
		let cart: Cart;
		if (cartId == -1) {
			this.shoppingCartReactiveService.createShoppingCart().pipe(
				map((c: Cart) => c.id),
				mergeMap((id: number) => {
					return this.shoppingCartReactiveService.putShoppingCartStream(id, this.product.code, this.selectedQuantity);
				})
			).subscribe((c:Cart) => {
							cart = c;
							localStorage.setItem('cartId', ''+cart.id);
						});
		} else {
			this.shoppingCartReactiveService.putShoppingCartStream(cartId, this.product.code, this.selectedQuantity).subscribe(c => {
				cart = c;
			});
		}
	}
}