package com.pruebatecnica.identity.core;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "identity")
public class IdentityDocument {

	@Id
	public int number;

	public ZonedDateTime experiDate;

	public ZonedDateTime emmisionDate;

	@Field("documentType")
	public DocumentType documentType;
}
