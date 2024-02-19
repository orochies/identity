package com.pruebatecnica.identity.core;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class DocumentTypeDto {

	@NotBlank(message = "El code es obligatorio.")
	public String code;

	@NotBlank(message = "El name es obligatorio.")
	public String name;

	public DocumentTypeDto() {

	}

	public DocumentTypeDto(DocumentType doc) {
		fromDocument(doc);
	}

	public DocumentTypeDto fromDocument(DocumentType doc) {
		Objects.requireNonNull(doc, "El parametro doc es null.");

		this.name = doc.getName();
		this.code = doc.getCode();

		return this;
	}

	public DocumentType toDocument() {
		return new DocumentType(name, code);
	}

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
