import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Headers, RequestOptions } from '@angular/http';
import { RouterModule } from '@angular/router';

import { Cart } from './cart';

import * as EventSource from 'eventsource';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { environment } from '../environments/environment';

@Injectable()
export class ShoppingCartReactiveService {
	cart: Cart;
	url: string = environment.apiEndpoint + '/cart';
  
	constructor(private http: HttpClient) { }
  
	createShoppingCart(): Observable<Cart> {
		let url = this.url;
		let httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
		let options = { headers: httpHeaders }; 
		return this.http.post(url, options).catch(this.handleErrorObservable);
	}

	getShoppingCartStream(cartId: number): Observable<Cart> {
		let url = this.url + '/' + cartId;
		let httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
		let options = { headers: httpHeaders }; 
		return this.http.get(url, options).catch(this.handleErrorObservable);
	}
  
	putShoppingCartStream(cartId: number, productCode: string, quantity: number): Observable<Cart> {
		let url = this.url + '/' + cartId + '?productCode=' + productCode + '&quantity=' + quantity;
		let httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
		let options = { headers: httpHeaders }; 
		return this.http.put(url, options).catch(this.handleErrorObservable);
	}
	
	deleteShoppingCartStream(cartId: number): Observable<any> {
		let url = this.url + '/' + cartId;
		let httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
		let options = { headers: httpHeaders }; 
		return this.http.delete(url, options).catch(this.handleErrorObservable);
	}
    
	private handleErrorObservable (error: Response | any) {
		console.log(error.message || error);
		return Observable.throw(error.message || error);
    }
}
