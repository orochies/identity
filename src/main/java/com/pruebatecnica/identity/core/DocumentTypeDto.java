package com.pruebatecnica.identity.core;

import jakarta.validation.constraints.NotBlank;

public class DocumentTypeDto {

	@NotBlank(message = "El code es obligatorio.")
	public String code;

	@NotBlank(message = "El name es obligatorio.")
	public String name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public DocumentTypeDto setCode(String code) {
		this.code = code;
		return this;
	}

	public DocumentTypeDto setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String toString() {
		return "DocumentTypeDto [code=" + code + ", name=" + name + "]";
	}

}
