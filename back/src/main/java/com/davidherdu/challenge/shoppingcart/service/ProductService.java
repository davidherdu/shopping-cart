package com.davidherdu.challenge.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidherdu.challenge.shoppingcart.model.Product;
import com.davidherdu.challenge.shoppingcart.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Flux<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Mono<Product> findByCode(String code) {
		return productRepository.findByCode(code);
	}
}
