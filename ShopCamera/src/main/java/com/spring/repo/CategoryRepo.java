package com.spring.repo;

import java.util.List;

import com.spring.domain.Category;

public interface CategoryRepo {
	public List<Category> getAll();
	public List<Object[]> getCateBySlide();
	public List<Category> getCategoryByIdToot(int idRoot);
}
