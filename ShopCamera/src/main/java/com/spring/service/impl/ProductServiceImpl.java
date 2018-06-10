package com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.City;
import com.spring.domain.Product;
import com.spring.repo.ProductRepo;
import com.spring.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;

	@Override
	public int add(Product p) {
		return productRepo.add(p);
	}

	@Override
	public List<Product> getAll() {

		return productRepo.getAll();

	}

	@Override
	public Product getProduct(int id) {
		return productRepo.getProduct(id);
	}

	@Override
	public List<City> getCity() {
		return productRepo.getCity();
	}

	@Override
	public List<Object[]> getProductBySlide(int id_category) {
		return productRepo.getProductBySlide(id_category);
	}

}
