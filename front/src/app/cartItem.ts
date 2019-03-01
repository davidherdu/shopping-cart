export class CartItem {
  code: string;
  quantity: number;

  constructor( code: string, quantity: number) {
    this.code = code;
    this.quantity = quantity;
  }
}
