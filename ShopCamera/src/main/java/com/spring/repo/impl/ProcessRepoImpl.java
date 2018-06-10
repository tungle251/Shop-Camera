package com.spring.repo.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.domain.Processing;
import com.spring.repo.ProcessingRepo;

@Repository
@Transactional
public class ProcessRepoImpl implements ProcessingRepo {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Processing> findAll() {
		Session session = this.sessionFactory.openSession();
		return session.createQuery("FROM Processing", Processing.class).getResultList();
	}

}
