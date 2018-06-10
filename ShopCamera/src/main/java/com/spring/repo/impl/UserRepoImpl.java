package com.spring.repo.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.RoleDetail;
import com.spring.domain.Users;
import com.spring.repo.UserRepo;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int insert(Users users) {
		Session session = sessionFactory.openSession();
		session.save(users);
		return users.getId();
	}

	@Override
	public Optional<Users> findByMail(String email) {
		Session session = sessionFactory.openSession();
		String sql = "select u.id, u.name, u.email, u.password, u.phone_number, u.address, u.id_role, u.active from users u where u.email = :email";
		Query<Users> query = session.createNativeQuery(sql, Users.class);
		query.setParameter("email", email);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public String roleName(int idUser) {

		Session session = sessionFactory.openSession();
		String sql = "select r.id, r.name from role_detail r where r.id =:idUser ";
		Query<RoleDetail> query = session.createNativeQuery(sql);
		query.setParameter(idUser, idUser);
		return query.getSingleResult().getName();
	}

	public List<RoleDetail> getRole() {
		Session session = sessionFactory.openSession();
		List<RoleDetail> r = session.createQuery("From Role_detail").getResultList();
		return r;
	}

	@Override
	public List<String> roleByUserId(int id) {
		Session session = sessionFactory.openSession();
		String sql = " select r.name from role_detail r where r.id =:id";
		Query<String> query = session.createNativeQuery(sql);
		query.setParameter("id", id);
		List<String> r = query.getResultList();
		return r;
	}

}
