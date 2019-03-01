package com.davidherdu.challenge.shoppingcart.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.davidherdu.challenge.shoppingcart.model.Product.CodeProduct;

public class SimpleCheckout implements Checkout {

	private final Map<CodeProduct, Double> basePrice = new HashMap<>();

	public void addPrice(CodeProduct codeProduct, double price) {
		basePrice.put(codeProduct, price);
	}

	@Override
	public double calculateTotal(List<CartItem> shoppingCart) {
		return shoppingCart.stream().mapToDouble(item -> basePrice.get(item.getCode()) * item.getQuantity()).sum();
	}

	@Override
	public CodeProduct getDiscountedItem() {
		return null;
	}
}