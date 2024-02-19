package com.pruebatecnica.identity.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class YearMonthDeserializer extends StdDeserializer<LocalDate> {

	private static final long serialVersionUID = 1L;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

	public YearMonthDeserializer() {
		this(null);
	}

	public YearMonthDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDate deserialize(JsonParser jsonparser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String date = jsonparser.getText();
		try {
			return YearMonth.parse(date, formatter).atEndOfMonth();
		} catch (DateTimeParseException e) {
			throw new AppException("NO se puede leer el formato de fecha. ", e);
		}
	}
}
