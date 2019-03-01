package com.davidherdu.challenge.shoppingcart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davidherdu.challenge.shoppingcart.model.Cart;
import com.davidherdu.challenge.shoppingcart.service.CartService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;
	
	@PostMapping
	public Mono<Cart> createCart() {
		logger.info("createCart");
		return cartService.createCart();
	}
	
	@PutMapping("/{idCart}")
	public Mono<ResponseEntity<Cart>> addProduct(final @PathVariable long idCart, 
												final @RequestParam(value = "productCode", required = true) String productCode, 
												final @RequestParam(value = "quantity", required = true) int quantity) {
		logger.info("addProduct to cart: " + idCart);
		return cartService.addProduct(idCart, productCode, quantity)
						.map(ResponseEntity::ok)
						.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{idCart}")
	public Mono<ResponseEntity<Cart>> getCart(@PathVariable long idCart) {
		logger.info("getting cart: " + idCart);
		return cartService.getCart(idCart)
						.map(ResponseEntity::ok)
						.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/total/{idCart}")
	public Mono<Double> getAmountCart(@PathVariable long idCart) {
		return cartService.getAmountCart(idCart);
	}
	
	@DeleteMapping("/{idCart}")
	public Mono<Void> deleteCart(@PathVariable long idCart) {
		logger.info("deleting cart: " + idCart);
		return cartService.deleteCart(idCart);
	}
}
