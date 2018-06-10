package com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.Processing;
import com.spring.repo.ProcessingRepo;
import com.spring.service.ProcessingService;
@Service
public class ProcessingServiceImp implements ProcessingService{

	@Autowired
	private ProcessingRepo processingRepo;
	
	@Override
	public List<Processing> findAll() {
		return this.processingRepo.findAll();
	}
}
