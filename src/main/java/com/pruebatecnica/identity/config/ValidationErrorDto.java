package com.pruebatecnica.identity.config;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDto {

	private List<String> globalErrors = new ArrayList<>();
	private List<FieldErrorDto> fieldErrors = new ArrayList<>();

	public ValidationErrorDto() {

	}

	public void addGlobalError(String error) {
		globalErrors.add(error);
	}

	public void addFieldError(String path, String message) {
		FieldErrorDto error = new FieldErrorDto(path, message);
		fieldErrors.add(error);
	}

	public int getErrorCount() {
		return globalErrors.size() + fieldErrors.size();
	}

	public List<String> getGlobalErrors() {
		return globalErrors;
	}

	public List<FieldErrorDto> getFieldErrors() {
		return fieldErrors;
	}
}
