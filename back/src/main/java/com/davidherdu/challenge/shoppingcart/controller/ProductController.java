package com.davidherdu.challenge.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidherdu.challenge.shoppingcart.model.Product;
import com.davidherdu.challenge.shoppingcart.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public Flux<Product> getProducts() {
		return productService.findAll();
	}
	
	@GetMapping("/{code}")
	public Mono<ResponseEntity<Product>> getProductByCode(@PathVariable String code) {
		return productService.findByCode(code)
							.map(ResponseEntity::ok)
							.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
