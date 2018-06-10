package com.spring.repo;

import java.util.List;
import java.util.Optional;

import com.spring.domain.Users;

public interface UserRepo {
	public int insert(Users users);

	public Optional<Users> findByMail(String email);

	public String roleName(int idUser);

	public List<String> roleByUserId(int id);

}
