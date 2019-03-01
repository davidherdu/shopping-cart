package com.davidherdu.challenge.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidherdu.challenge.shoppingcart.model.Cart;
import com.davidherdu.challenge.shoppingcart.model.CartItem;
import com.davidherdu.challenge.shoppingcart.model.Product.CodeProduct;
import com.davidherdu.challenge.shoppingcart.repository.CartRepository;

import reactor.core.publisher.Mono;

@Service
public class CartService {

	@Autowired
	private CheckoutService checkoutService;
	@Autowired
	private CartRepository cartRepository;

	public Mono<Cart> createCart() {
		return cartRepository.save(new Cart());
	}

	public Mono<Cart> addProduct(long idCart, String productCode, int quantity) {
		return Mono.zip(objects -> addOrUpdateProductInCart((Cart) objects[0], productCode, quantity), 
						cartRepository.findById(idCart))
					.flatMap(cart -> cartRepository.save(cart))
					.flatMap(cart -> checkoutService.checkout(cart));
	}

	public Mono<Cart> getCart(long idCart) {
		return Mono.zip(objects -> ((Cart) objects[0]), 
						cartRepository.findById(idCart))
					.flatMap(cart -> checkoutService.checkout(cart));
	}

	public Mono<Double> getAmountCart(long idCart) {
		return Mono.zip(objects -> ((Cart) objects[0]).getTotal(), getCart(idCart));
	}

	public Mono<Void> deleteCart(long idCart) {
		return cartRepository.delete(idCart);
	}

	private Cart addOrUpdateProductInCart(Cart cart, String productCode, Integer quantity) {
		if (isCartContainProduct(cart, productCode)) {
			incrementProductQuantity(cart, productCode, quantity);
		} else {
			addNewProductToCart(cart, productCode, quantity);
		}

		return cart;
	}

	private boolean isCartContainProduct(Cart cart, String productCode) {
		return cart.getProducts().stream().anyMatch(p -> p.getCode().toString().equals(productCode));
	}

	private void incrementProductQuantity(Cart cart, String productCode, Integer quantity) {
		cart.getProducts().stream()
						.filter(p -> p.getCode().toString().equals(productCode))
						.forEach(cartItem -> cartItem.setQuantity(cartItem.getQuantity() + quantity));
		cart.getProducts().removeIf(p -> p.getCode().toString().equals(productCode) && p.getQuantity() < 1);
	}

	private void addNewProductToCart(Cart cart, String productCode, Integer quantity) {
		CartItem item = new CartItem();
		item.setCode(CodeProduct.valueOf(productCode));
		item.setQuantity(quantity);
		cart.getProducts().add(item);
	}
}
