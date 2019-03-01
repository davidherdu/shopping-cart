package com.davidherdu.challenge.shoppingcart.repository;

import com.davidherdu.challenge.shoppingcart.model.Discount;

import reactor.core.publisher.Flux;

public interface DiscountRepository {

	Flux<Discount> findAll();
}
