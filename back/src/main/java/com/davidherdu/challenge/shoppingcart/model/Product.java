package com.davidherdu.challenge.shoppingcart.model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 3251603346601915694L;

	public enum CodeProduct {
		VOUCHER, TSHIRT, MUG;
	}
	
	private CodeProduct code;
	private String name;
	private double price;

	public CodeProduct getCode() {
		return code;
	}

	public void setCode(CodeProduct code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
