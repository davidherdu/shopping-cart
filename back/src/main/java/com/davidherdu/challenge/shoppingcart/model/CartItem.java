package com.davidherdu.challenge.shoppingcart.model;

import java.io.Serializable;

import com.davidherdu.challenge.shoppingcart.model.Product.CodeProduct;

public class CartItem implements Serializable {
	
	private static final long serialVersionUID = -4324785332157227473L;
	
	private CodeProduct code;
	private int quantity;

	public CodeProduct getCode() {
		return code;
	}

	public void setCode(CodeProduct code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
