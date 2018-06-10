package com.spring.repo.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Category;
import com.spring.repo.CategoryRepo;

@Repository
@Transactional
public class CategoryRepoImpl implements CategoryRepo {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() {
		Session session = this.sessionFactory.openSession();
		return session.createQuery("FROM Category").getResultList();
	}

	@Override
	public List<Object[]> getCateBySlide() {
		Session session = this.sessionFactory.openSession();
		// String hql = "FROM Slide";
		String hql = "select c.id, c.name, c.id_root, c.status,c.menu, c.icon,c.link, s.img from Slide as s inner join Category as c on s.id_category = c.id where s.status = 1";
		List<Object[]> list = session.createQuery(hql).list();
		return list;
	}

	public Category getCategory(int id) {
		Session session = this.sessionFactory.openSession();
		Category cate = session.load(Category.class, id);
		return cate;
	}

	@Override
	public List<Category> getCategoryByIdToot(int idRoot) {
		Session session = this.sessionFactory.openSession();
		String sql = "select c.id, c.name, c.id_root, c.status, c.menu, c.icon, c.link from category c where c.id_root = :idRoot";
		Query<Category> query = session.createNativeQuery(sql, Category.class);
		query.setParameter("idRoot", idRoot);
		List<Category> list = query.getResultList();
		return list;
	}
}
