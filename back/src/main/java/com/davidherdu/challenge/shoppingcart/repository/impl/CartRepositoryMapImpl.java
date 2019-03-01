package com.davidherdu.challenge.shoppingcart.repository.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.davidherdu.challenge.shoppingcart.model.Cart;
import com.davidherdu.challenge.shoppingcart.repository.CartRepository;

import reactor.core.publisher.Mono;

@Repository
public class CartRepositoryMapImpl implements CartRepository {

	private Map<Long, Cart> carts;

	public CartRepositoryMapImpl() {
		this.carts = new ConcurrentHashMap<>();
	}
	
	@Override
	public Mono<Cart> save(Cart cart) {
		this.carts.put(cart.getId(), cart);
		return Mono.just(cart);
	}
	
	@Override
	public Mono<Cart> findById(long idCart) {
		return Mono.justOrEmpty(this.carts.get(idCart));
	}
	
	@Override
	public Mono<Void> delete(long idCart) {
		this.carts.remove(idCart);
		return Mono.empty();
	}
}
