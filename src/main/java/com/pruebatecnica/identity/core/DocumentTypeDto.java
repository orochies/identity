package com.pruebatecnica.identity.core;

import jakarta.validation.constraints.NotBlank;

public class DocumentTypeDto {

	@NotBlank(message = "El code es obligatorio.")
	public String code;

	@NotBlank(message = "El name es obligatorio.")
	public String name;

}
