package com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.Category;
import com.spring.repo.CategoryRepo;
import com.spring.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public List<Category> findAll() {
		return categoryRepo.getAll();
	}

	@Override
	public List<Object[]> getCateBySlide() {
		return categoryRepo.getCateBySlide();
	}

}
