package com.spring.repo.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.City;
import com.spring.domain.Product;
import com.spring.repo.ProductRepo;

@Repository
@Transactional
public class ProductRepoImpl implements ProductRepo {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int add(Product p) {
		Session session = this.sessionFactory.openSession();
		session.save(p);
		return p.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> p = session.createQuery("From Product").getResultList();
		return p;
	}

	@Override
	public Product getProduct(int id) {
		Session session = this.sessionFactory.openSession();
		Product p = session.get(Product.class, id);
		return p;
	}

	@Override
	public List<City> getCity() {
		Session session = this.sessionFactory.openSession();
		List<City> listCity = session.createQuery("From City").getResultList();
		return listCity;
	}

	@Override
	public List<Object[]> getProductBySlide(int id_category) {
		Session session = this.sessionFactory.openSession();
		String hql = "select p.id, p.id_category, p.name, p.price, p.eval, p.viewer, p.status, p.quatity, p.img, p.detail_product, p.digital, p.link from Slide s INNER JOIN Slide_product sp ON sp.id_slide = s.id INNER JOIN Product p ON sp.id_product = p.id where s.id_category = :id and p.status=1";
		Query<Object[]> query = session.createQuery(hql);
		query.setParameter("id", id_category);
		List<Object[]> list = query.getResultList();

		return list;
	}

	public int pageNumber(int id_cate) {
		Session session = this.sessionFactory.getCurrentSession();
		// String hql = "from Product p where p.id_category = :id_cate";
		// Query<Product> query = session.createQuery(hql);
		// query.setParameter("id_cate", id_cate);
		String sql2 = "SELECT p.id, p.id_category, p.name, p.price, p.eval, p.viewer, p.status, p.quatity, p.img, p.id_provider, p.detail_product, p.digital, p.link  from product p"
				+ " WHERE status=1 and (id_category = :id_cate OR id_category IN (SELECT id FROM category WHERE id_root IN (SELECT id FROM category WHERE id = :id_cate OR id_root IN (SELECT id FROM category WHERE id = :id_cate ))))";
		Query<Product> query = session.createNativeQuery(sql2, Product.class);
		query.setParameter("id_cate", id_cate);
		List<Product> list = query.getResultList();
		int count = list.size();
		if (count % 12 == 0) {
			return (count / 12);
		} else {
			return (count / 12) + 1;
		}
	}

	public List<Product> getProductByCate(int id_cate, int pageNumber, int pageSize) {

		int offset = (pageNumber - 1) * pageSize;
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Product p where p.id_category = :id_cate";
		// String sql = "select p.id, p.id_category, p.name, p.price, p.eval, p.viewer,
		// p.status, p.quatity, p.img, p.id_provider, p.detail_product, p.digital,
		// p.link from product p where p.id_category = :id_cate limit :limit offset
		// :offset";
		// Query<Product> query = session.createQuery(hql);

		String sql2 = "SELECT p.id, p.id_category, p.name, p.price, p.eval, p.viewer, p.status, p.quatity, p.img, p.id_provider, p.detail_product, p.digital, p.link  from product p"
				+ " WHERE status=1 and (id_category = :id_cate OR id_category IN (SELECT id FROM category WHERE id_root IN (SELECT id FROM category WHERE id = :id_cate OR id_root IN (SELECT id FROM category WHERE id = :id_cate )))) limit :limit  offset :offset";
		Query<Product> query = session.createNativeQuery(sql2, Product.class);
		query.setParameter("id_cate", id_cate);
		query.setParameter("limit", pageSize);
		query.setParameter("offset", offset);
		List<Product> list = query.getResultList();
		return list;
	}

}
