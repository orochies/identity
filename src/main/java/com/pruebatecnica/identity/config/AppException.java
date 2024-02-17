package com.pruebatecnica.identity.config;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppException(String msg, Object... params) {
		super(String.format(msg, params));
	}

	public AppException(String msg) {
		super(msg);
	}

	public AppException(String msg, Throwable inner) {
		super(msg, inner);
	}

}
