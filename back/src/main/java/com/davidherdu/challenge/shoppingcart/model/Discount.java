package com.davidherdu.challenge.shoppingcart.model;

import com.davidherdu.challenge.shoppingcart.model.Product.CodeProduct;

public class Discount {

	public enum TypeDiscount {
		BULK_PURCHASE, N_GET_M
	}
	
	private TypeDiscount type;
	private CodeProduct product;
	private int amountMin;
	private double discount;
	private boolean active;
	
	public TypeDiscount getType() {
		return type;
	}
	
	public void setType(TypeDiscount type) {
		this.type = type;
	}
	
	public CodeProduct getProduct() {
		return product;
	}
	
	public void setProduct(CodeProduct product) {
		this.product = product;
	}
	
	public int getAmountMin() {
		return amountMin;
	}
	
	public void setAmountMin(int amountMin) {
		this.amountMin = amountMin;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
