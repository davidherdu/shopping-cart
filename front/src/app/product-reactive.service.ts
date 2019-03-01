import { Injectable } from '@angular/core';

import { Product } from './product';

import * as EventSource from 'eventsource';
import {Observable} from 'rxjs/Observable';

import { environment } from '../environments/environment';

@Injectable()
export class ProductReactiveService {
  products: Product[] = [];
  url: string = environment.apiEndpoint + '/product';

  getProductStream(): Observable<Array<Product>> {
    
    return Observable.create((observer) => {
		this.products = [];
		let url = this.url;

		let eventSource = new EventSource(url);
		eventSource.onmessage = (event) => {
			console.debug('Received event: ', event);
			let json = JSON.parse(event.data);
			this.products.push(new Product(json['code'], json['name'], json['price']));
			observer.next(this.products);
		};
		eventSource.onerror = (error) => {
			// readyState === 0 (closed) means the remote source closed the connection,
			// so we can safely treat it as a normal situation. Another way of detecting the end of the stream
			// is to insert a special element in the stream of events, which the client can identify as the last one.
			if(eventSource.readyState === 0) {
				console.log('The stream has been closed by the server.');
				eventSource.close();
				observer.complete();
			} else {
				observer.error('EventSource error: ' + error);
			}
		}
    });
  }
}
