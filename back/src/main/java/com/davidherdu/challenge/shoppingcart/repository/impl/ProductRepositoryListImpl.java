package com.davidherdu.challenge.shoppingcart.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.davidherdu.challenge.shoppingcart.model.Product;
import com.davidherdu.challenge.shoppingcart.model.ShoppingCartConfiguration;
import com.davidherdu.challenge.shoppingcart.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryListImpl implements ProductRepository {

	private List<Product> products;
	
	public ProductRepositoryListImpl(ShoppingCartConfiguration shoppingCartConfiguration) {
		this.products = shoppingCartConfiguration.getProducts();
	}

	@Override
	public Flux<Product> findAll() {
		return Flux.fromIterable(this.products);
	}

	@Override
	public Mono<Product> findByCode(String code) {
		return Mono.justOrEmpty(this.products.stream()
											.filter(p -> p.getCode().toString().equals(code))
											.findFirst()
											.orElse(null));
	}

}
