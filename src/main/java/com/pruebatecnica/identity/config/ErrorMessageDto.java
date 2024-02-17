package com.pruebatecnica.identity.config;

public class ErrorMessageDto {

	private String message = "";

	public ErrorMessageDto() {
	}

	public ErrorMessageDto(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}
}
