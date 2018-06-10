package com.spring.securityConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.domain.Users;

public class UserPrincipal implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user;

	public UserPrincipal(Users user) {
		super();
		this.user = user;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		if (user.getId_role() == 1) {
			list.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
		}
		if (user.getId_role() == 2) {
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return list;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getActive() == 1;
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getActive() == 1;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.getActive() == 1;
	}

	@Override
	public boolean isEnabled() {
		return user.getActive() == 1;
	}

}
