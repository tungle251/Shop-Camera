package com.spring.securityConfiguration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.domain.Users;
import com.spring.repo.impl.UserRepoImpl;

@Service
public class UserDetailServiceImp implements UserDetailsService {
	@Autowired
	private UserRepoImpl userRepoImpl;

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Optional<Users> users = this.userRepoImpl.findByMail(mail);
		if (!users.isPresent()) {
			throw new UsernameNotFoundException("Email not found!");
		}
		System.err.println(users);
		return new UserPrincipal(users.get());
	}

}
