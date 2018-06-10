package com.spring.service;

import java.util.List;

import com.spring.domain.City;
import com.spring.domain.Product;

public interface ProductService {
	public int add(Product p);

	public List<Product> getAll();

	public Product getProduct(int id);

	public List<City> getCity();

	public List<Object[]> getProductBySlide(int id_category);
}
