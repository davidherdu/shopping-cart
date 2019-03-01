package com.davidherdu.challenge.shoppingcart.model;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Cart {

	private final long id;
	private final List<CartItem> products;
	private double total;

	public Cart() {
		this.id = generateUniqueId();
		this.products = new LinkedList<>();
	}

	public long getId() {
		return id;
	}

	public List<CartItem> getProducts() {
		return products;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	private Long generateUniqueId() {
		long val = -1;
		do {
			final UUID uid = UUID.randomUUID();
			final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
			buffer.putLong(uid.getLeastSignificantBits());
			buffer.putLong(uid.getMostSignificantBits());
			final BigInteger bi = new BigInteger(buffer.array());
			val = bi.longValue();
		}
		// We also make sure that the ID is in positive space, if its not we simply
		// repeat the process
		while (val < 0);
		return val / 1000;
	}
}
