package com.spring.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

	private HashMap<Integer, Item> cartItems;
	private List<Product> list;

	public Cart() {
		cartItems = new HashMap<>();
		list = new ArrayList<>();
	}

	public Cart(HashMap<Integer, Item> cartItems) {
		this.cartItems = cartItems;
	}

	public HashMap<Integer, Item> getCartItems() {
		return cartItems;
	}

	public void setCartItems(HashMap<Integer, Item> cartItems) {
		this.cartItems = cartItems;
	}

	public List<Product> getList() {
		for (Map.Entry<Integer, Item> listItem : cartItems.entrySet()) {
			list.add(listItem.getValue().getProduct());
		}
		return list;
	}

	public void doCart(Integer key, Item item) {
		boolean bln = cartItems.containsKey(key);
		if (bln) {
			int quantity_old = item.getQuantity();
			item.setQuantity(quantity_old + 1);
			cartItems.put(item.getProduct().getId(), item);
		} else {
			cartItems.put(item.getProduct().getId(), item);
		}
	}

	public void removeToCart(int product) {
		boolean bln = cartItems.containsKey(product);
		if (bln) {
			cartItems.remove(product);
		}
	}

	public int countItem() {
		int count = 0;
		count = cartItems.size();
		return count;
	}

	public int total() {
		int count = 0;
		for (Map.Entry<Integer, Item> list : cartItems.entrySet()) {
			count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
		}
		return count;
	}
	
}
