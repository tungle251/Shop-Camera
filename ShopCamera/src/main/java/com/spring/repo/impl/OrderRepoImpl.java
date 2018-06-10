package com.spring.repo.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import com.spring.domain.News;
import com.spring.domain.Order;
import com.spring.repo.OrderRepo;

@Repository
@Transactional
public class OrderRepoImpl implements OrderRepo {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Order> getListOrder() {
		Session session = this.sessionFactory.openSession();
		// String sql = "Select o.id, o.code_order, o.id_user, o.name_customer, o.email,
		// o.id_process, o.active, o.address, o.phone_number, o.key_sales_off, o.total
		// from order o";
		// Query<Order> query = session.createQuery(sql);
		List<Order> list = session.createQuery("From Order").getResultList();
		// List<Order> list = (List<Order>) query.getResultList();

		return list;
	}

	@Override
	public List<News> getListNews() {
		Session session = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<News> query = session.createQuery("From News").getResultList();
		return query;
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public int getListOrder() {
	// Session session = this.sessionFactory.openSession();
	// List<Order> list = (List<Order>) session.createQuery("From
	// Order").getResultList();
	//
	// return list.size();
	// }

	@Override
	public Order getOrder(int id) {
		Session session = this.sessionFactory.openSession();
		Order o = (Order) session.get(Order.class, id);
		return o;

	}

	@Override
	public int insertOrder(Order order) {
		Session session = this.sessionFactory.openSession();
		session.save(order);
		return order.getId();
	}

}
