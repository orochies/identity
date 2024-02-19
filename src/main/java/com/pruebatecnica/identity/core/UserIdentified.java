package com.pruebatecnica.identity.core;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserIdentified {

	@NotBlank
	@Size(max = 30)
	private String username;

	@NotBlank
	private String password;

	public UserIdentified() {
		super();
	}

	public UserIdentified(String username, String password) {

		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserIdentified setUsername(String username) {
		this.username = username;
		return this;
	}

	public UserIdentified setPassword(String password) {
		this.password = password;
		return this;
	}

}
