package com.davidherdu.challenge.shoppingcart.repository;

import com.davidherdu.challenge.shoppingcart.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

	Flux<Product> findAll();
	Mono<Product> findByCode(String code);
}
