package com.pruebatecnica.identity.security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailIdentity implements UserDetailsService {

	private String defaultUsername;
	private String defaultPassword;

	public UserDetailIdentity(@Value("${app.default.username}") String defaultUsername,
			@Value("${app.default.password}") String defaultPassword) {

		this.defaultUsername = defaultUsername;
		this.defaultPassword = defaultPassword;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// @formatter:off
		if(defaultUsername.equals(username)) {
			return User.builder()
					.username(defaultUsername)
					.password(defaultPassword)
					.roles("USER")
					.build();
		}
		// @formatter:on

		throw new UsernameNotFoundException("El usuario con nombre " + username + "no existe.");
	}

}
