package com.davidherdu.challenge.shoppingcart.model;

import java.util.List;

import com.davidherdu.challenge.shoppingcart.model.Product.CodeProduct;

public interface Checkout {
	double calculateTotal(List<CartItem> shoppingCart);
	CodeProduct getDiscountedItem();
}