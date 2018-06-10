package com.spring.cart;

import com.spring.domain.Product;

public class CartInfo {

	private Product product;
	private int quantity;

	public CartInfo() {
		super();

	}

	public CartInfo(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		return this.product.getPrice() * this.quantity;
	}

}