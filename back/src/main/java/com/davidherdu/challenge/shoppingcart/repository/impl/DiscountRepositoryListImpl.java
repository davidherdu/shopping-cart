package com.davidherdu.challenge.shoppingcart.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.davidherdu.challenge.shoppingcart.model.Discount;
import com.davidherdu.challenge.shoppingcart.model.ShoppingCartConfiguration;
import com.davidherdu.challenge.shoppingcart.repository.DiscountRepository;

import reactor.core.publisher.Flux;

@Repository
public class DiscountRepositoryListImpl implements DiscountRepository {

	private List<Discount> discounts;

	public DiscountRepositoryListImpl(ShoppingCartConfiguration shoppingCartConfiguration) {
		this.discounts = shoppingCartConfiguration.getDiscounts();
	}
	
	@Override
	public Flux<Discount> findAll() {
		return Flux.fromIterable(this.discounts);
	}
}
