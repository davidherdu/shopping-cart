import { CartItem } from './cartItem';

export class Cart {
  id: number;
  products: Array<CartItem>;
  total: number;

  constructor( id: number, products: Array<CartItem>, total: number) {
    this.id = id;
    this.products = products;
    this.total = total;
  }
}
