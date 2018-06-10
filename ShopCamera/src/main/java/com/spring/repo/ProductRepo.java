package com.spring.repo;

import java.util.List;

import com.spring.domain.City;
import com.spring.domain.Product;

public interface ProductRepo {
	public int add(Product p);

	public List<Product> getAll();

	public Product getProduct(int id);

	public List<City> getCity();

	public List<Object[]> getProductBySlide(int id_category);
}
