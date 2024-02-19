package com.pruebatecnica.identity.core;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pruebatecnica.identity.config.YearMonthDeserializer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IdentityDocumentDto {

	private String id;

	@NotBlank(message = "El number es obligatorio.")
	private String number;

	@NotNull(message = "El experiDate es obligatorio.")
	@JsonDeserialize(using = YearMonthDeserializer.class)
	@JsonFormat(pattern = "MM/yy")
	private LocalDate expiryDate;

	@NotNull(message = "El emissionDate es obligatorio.")
	private ZonedDateTime emissionDate;

	@Valid
	private DocumentTypeDto documentType;

	public IdentityDocumentDto() {

	}

	public IdentityDocumentDto(IdentityDocument doc) {
		fromDocument(doc);
	}

	public IdentityDocumentDto fromDocument(IdentityDocument doc) {
		Objects.requireNonNull(doc, "El parametro doc es null.");

		this.id = doc.getId();
		this.number = doc.getNumber();
		this.expiryDate = doc.getExpiryDate();
		this.emissionDate = doc.getEmissionDate();
		this.documentType = new DocumentTypeDto(doc.getDocumentType());

		return this;
	}

	public IdentityDocument toDocument() {

		// @formatter:off
		return new IdentityDocument()
				.setId(id)
				.setNumber(number)
				.setExpiryDate(expiryDate)
				.setEmissionDate(emissionDate)
				.setDocumentType(documentType.toDocument());
		// @formatter:on
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

	public DocumentTypeDto getDocumentType() {
		return documentType;
	}

	public IdentityDocumentDto setId(String id) {
		this.id = id;
		return this;
	}

	public IdentityDocumentDto setNumber(String number) {
		this.number = number;
		return this;
	}

	public IdentityDocumentDto setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
		return this;
	}

	public IdentityDocumentDto setEmissionDate(ZonedDateTime emissionDate) {
		this.emissionDate = emissionDate;
		return this;
	}

	public IdentityDocumentDto setDocumentType(DocumentTypeDto documentType) {
		this.documentType = documentType;
		return this;
	}

	@Override
	public String toString() {
		return "IdentityDocumentDto [id=" + id + ", number=" + number + ", expiryDate=" + expiryDate + ", emissionDate="
				+ emissionDate + ", documentType=" + documentType + "]";
	}

}
