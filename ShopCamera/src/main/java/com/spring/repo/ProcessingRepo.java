package com.spring.repo;

import java.util.List;

import com.spring.domain.Processing;

public interface ProcessingRepo {
	public List<Processing> findAll();
}
