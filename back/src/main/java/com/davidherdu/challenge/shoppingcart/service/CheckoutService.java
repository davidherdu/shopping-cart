package com.davidherdu.challenge.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidherdu.challenge.shoppingcart.model.BulkPurchaseDiscount;
import com.davidherdu.challenge.shoppingcart.model.BuyNgetMFreeDiscount;
import com.davidherdu.challenge.shoppingcart.model.Cart;
import com.davidherdu.challenge.shoppingcart.model.CartItem;
import com.davidherdu.challenge.shoppingcart.model.Checkout;
import com.davidherdu.challenge.shoppingcart.model.Discount;
import com.davidherdu.challenge.shoppingcart.model.Product;
import com.davidherdu.challenge.shoppingcart.model.SimpleCheckout;
import com.davidherdu.challenge.shoppingcart.model.Discount.TypeDiscount;
import com.davidherdu.challenge.shoppingcart.model.Product.CodeProduct;
import com.davidherdu.challenge.shoppingcart.repository.DiscountRepository;
import com.davidherdu.challenge.shoppingcart.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class CheckoutService {

	@Autowired
	private DiscountRepository discountRepository;
	@Autowired
	private ProductRepository productRepository;

	public Mono<Cart> checkout(Cart cart) {
		List<Checkout> checkouts = new ArrayList<>();
		SimpleCheckout simpleCheckout = new SimpleCheckout();

		initCheckout(checkouts, simpleCheckout);

		double total = 0;

		for (CartItem item : cart.getProducts()) {
			boolean discountApplied = false;
			for (Checkout checkout : checkouts) {
				if (checkout.getDiscountedItem().equals(item.getCode())) {
					total += checkout.calculateTotal(cart.getProducts());
					discountApplied = true;
				}
			}
			if (!discountApplied) {
				total += simpleCheckout.calculateTotal(cart.getProducts().stream()
						.filter(itemCart -> itemCart.getCode().equals(item.getCode())).collect(Collectors.toList()));
			}
		}

		cart.setTotal(total);
		return Mono.just(cart);
	}

	private void initCheckout(List<Checkout> checkouts, SimpleCheckout simpleCheckout) {
		final List<Product> products = new ArrayList<>();
		productRepository.findAll().subscribe(products::add);

		products.forEach(product -> {
			simpleCheckout.addPrice(product.getCode(), product.getPrice());
			searchCheckout(checkouts, simpleCheckout, product.getCode(), product.getPrice());
		});
	}

	private void searchCheckout(List<Checkout> checkouts, SimpleCheckout pricing, CodeProduct code, double price) {
		final List<Discount> discounts = new ArrayList<>();
		discountRepository.findAll().subscribe(discounts::add);

		discounts.forEach(discount -> {
			if (discount.isActive() && discount.getProduct().equals(code)) {
				if (discount.getType().equals(TypeDiscount.N_GET_M)) {
					checkouts.add(new BuyNgetMFreeDiscount(pricing, discount.getProduct(), discount.getAmountMin(),
							discount.getDiscount()));
				}
				if (discount.getType().equals(TypeDiscount.BULK_PURCHASE)) {
					checkouts.add(new BulkPurchaseDiscount(pricing, discount.getProduct(), discount.getAmountMin(),
							discount.getDiscount(), price));
				}
			}
		});
	}
}
