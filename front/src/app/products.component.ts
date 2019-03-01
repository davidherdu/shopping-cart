import { Component, OnInit } from '@angular/core';

import { Product } from './product';
import { ProductReactiveService } from './product-reactive.service';

import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-component-products',
  providers: [ProductReactiveService],
  templateUrl: './products.component.html'
})
export class ProductsComponent implements OnInit {
	products: Observable<Product[]>;
	selectedProduct: Product;
	mode: String;

	constructor(private productReactiveService: ProductReactiveService) {
		this.mode = "reactive";
	}

	requestProductStream(): void {
		this.products = this.productReactiveService.getProductStream();
	}

	onSelect(product: Product): void {
		this.selectedProduct = product;
	}
	
	ngOnInit(): void {
        this.requestProductStream();
    }
	
	disableButton() {
		return localStorage.getItem('cartId') === null;
	}
}
