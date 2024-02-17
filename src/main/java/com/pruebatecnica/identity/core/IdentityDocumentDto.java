package com.pruebatecnica.identity.core;

import java.time.ZonedDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class IdentityDocumentDto {

	@NotBlank(message = "El number es obligatorio.")
	public int number;

	@NotBlank(message = "El experiDate es obligatorio.")
	public ZonedDateTime experiDate;

	@NotBlank(message = "El emmisionDate es obligatorio.")
	public ZonedDateTime emmisionDate;

	@Valid
	public DocumentType documentType;
}
