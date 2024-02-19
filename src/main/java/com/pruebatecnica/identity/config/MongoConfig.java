package com.pruebatecnica.identity.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.pruebatecnica.identity.config.converters.ZonedDateTimeReadConverter;
import com.pruebatecnica.identity.config.converters.ZonedDateTimeWriteConverter;

@Configuration
public class MongoConfig {

	private static final Logger log = LoggerFactory.getLogger(MongoConfig.class);

	@Bean
	public MongoCustomConversions customConversions() {

		log.debug("--- Registering Custom MongoDB Converters");

		List<Converter<?, ?>> converters = new ArrayList<>();

		converters.add(new ZonedDateTimeReadConverter());
		converters.add(new ZonedDateTimeWriteConverter());

		return new MongoCustomConversions(converters);
	}
}