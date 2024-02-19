package com.pruebatecnica.identity.core;

public class DocumentType {

	public String code;
	public String name;

	protected DocumentType(String code, String name) {

		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public DocumentType setCode(String code) {
		this.code = code;
		return this;
	}

	public DocumentType setName(String name) {
		this.name = name;
		return this;
	}

}
