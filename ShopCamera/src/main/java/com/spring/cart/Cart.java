package com.spring.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.domain.Product;

public class Cart {

	private int orderNum;

	private List<CartInfo> cartLines = new ArrayList<CartInfo>();
	private int totalPrice;

	public Cart() {

	}

	public int getTotalPrice() {
		for (CartInfo line : this.cartLines) {
			totalPrice += line.getAmount();
		}
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public List<CartInfo> getCartLines() {
		return this.cartLines;
	}

	private CartInfo findLineByCode(int code) {
		for (CartInfo line : this.cartLines) {
			if (line.getProduct().getId() == code) {
				return line;
			}
		}
		return null;
	}

	@Autowired
	public void addProduct(Product product, int quantity) {
		CartInfo line = this.findLineByCode(product.getId());

		if (line == null) {
			line = new CartInfo();
			line.setQuantity(0);
			line.setProduct(product);
			this.cartLines.add(line);
		}
		int newQuantity = line.getQuantity() + quantity;
		if (newQuantity <= 0) {
			this.cartLines.remove(line);
		} else {
			line.setQuantity(newQuantity);
		}
	}

	public void validate() {

	}

	public void updateProduct(int code, int quantity) {
		CartInfo line = this.findLineByCode(code);

		if (line != null) {
			if (quantity <= 0) {
				this.cartLines.remove(line);
			} else {
				line.setQuantity(quantity);
			}
		}
	}

	public void removeProduct(Product product) {
		CartInfo line = this.findLineByCode(product.getId());
		if (line != null) {
			this.cartLines.remove(line);
		}
	}

	public boolean isEmpty() {
		return this.cartLines.isEmpty();
	}

	public int getQuantityTotal() {
		int quantity = 0;
		for (CartInfo line : this.cartLines) {
			quantity += line.getQuantity();
		}
		return quantity;
	}

	public int getProductTotal() {
		int total = 0;
		total = cartLines.size();
		return total;
	}

	public int getAmountTotal() {
		int total = 0;
		for (CartInfo line : this.cartLines) {
			total += line.getAmount();
		}
		return total;
	}

	public void updateQuantity(Cart cart) {
		if (cart != null) {
			List<CartInfo> lines = cart.getCartLines();
			for (CartInfo line : lines) {
				this.updateProduct(line.getProduct().getId(), line.getQuantity());
			}
		}

	}

}