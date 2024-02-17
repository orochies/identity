package com.pruebatecnica.identity.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	public static final String API_PATH = "/api";

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

		log.debug("Registering custom ArgumentResolver.");
	}
}