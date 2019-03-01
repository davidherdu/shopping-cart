package com.davidherdu.challenge.shoppingcart.repository;

import com.davidherdu.challenge.shoppingcart.model.Cart;

import reactor.core.publisher.Mono;

public interface CartRepository {

	Mono<Cart> save(Cart cart);
	Mono<Cart> findById(long idCart);
	Mono<Void> delete(long idCart);
}