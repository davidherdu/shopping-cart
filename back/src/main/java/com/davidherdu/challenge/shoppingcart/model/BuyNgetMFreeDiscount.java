package com.davidherdu.challenge.shoppingcart.model;

import java.util.List;
import java.util.stream.Collectors;

import com.davidherdu.challenge.shoppingcart.model.Product.CodeProduct;

public class BuyNgetMFreeDiscount implements Checkout {

	private final Checkout baseCheckout;
	private final CodeProduct discountedItem;
	private final int minimumAmount;
	private final double discountValue;

	public BuyNgetMFreeDiscount(Checkout base, CodeProduct item, int min, double discount) {
		this.baseCheckout = base;
		this.discountedItem = item;
		this.minimumAmount = min;
		this.discountValue = discount;
	}

	@Override
	public double calculateTotal(List<CartItem> shoppingCart) {
		List<CartItem> listOfDiscountedItem = shoppingCart.stream().filter(item -> item.getCode().equals(discountedItem)).collect(Collectors.toList());
		int count = listOfDiscountedItem.get(0).getQuantity();
		
		double deduction = (count / minimumAmount) * discountValue;
		return baseCheckout.calculateTotal(listOfDiscountedItem) - deduction;
	}
	
	@Override
	public CodeProduct getDiscountedItem() {
		return discountedItem;
	}
}