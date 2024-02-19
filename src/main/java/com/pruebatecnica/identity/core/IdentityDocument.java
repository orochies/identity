package com.pruebatecnica.identity.core;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "identity")
public class IdentityDocument {

	@Id
	private String id;

	private String number;

	private LocalDate expiryDate;

	private ZonedDateTime emissionDate;

	private DocumentType documentType;

	protected IdentityDocument() {

	}

	public String getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public ZonedDateTime getEmissionDate() {
		return emissionDate;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public IdentityDocument setId(String id) {
		this.id = id;
		return this;
	}

	public IdentityDocument setNumber(String number) {
		this.number = number;
		return this;
	}

	public IdentityDocument setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
		return this;
	}

	public IdentityDocument setEmissionDate(ZonedDateTime emissionDate) {
		this.emissionDate = emissionDate;
		return this;
	}

	public IdentityDocument setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
		return this;
	}

}
