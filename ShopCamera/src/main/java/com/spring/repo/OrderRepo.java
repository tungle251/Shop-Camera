package com.spring.repo;

import java.util.List;

import org.springframework.web.context.request.WebRequest;

import com.spring.domain.News;
import com.spring.domain.Order;

public interface OrderRepo {
	// public int getListOrder();
	public Order getOrder(int id);

	public List<Order> getListOrder();

	public List<News> getListNews();

	public int insertOrder(Order order);
}
