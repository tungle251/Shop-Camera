package com.spring.service;

import java.util.List;

import com.spring.domain.Category;
import com.spring.domain.Slide;

public interface CategoryService {
	public List<Category> findAll();
	public List<Object[]> getCateBySlide();
}
