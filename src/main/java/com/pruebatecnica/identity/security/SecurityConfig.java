package com.pruebatecnica.identity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pruebatecnica.identity.config.AppConfig;
import com.pruebatecnica.identity.security.filters.JwtAuthenticationFilter;
import com.pruebatecnica.identity.security.filters.JwtAuthorizationFilter;
import com.pruebatecnica.identity.security.jwt.JwtUtilities;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private JwtUtilities jwtUtils;

	private JwtAuthorizationFilter authFilter;

	private UserDetailsService userDetailsService;

	public SecurityConfig(JwtUtilities jwtUtils, JwtAuthorizationFilter authFilter,
			UserDetailsService userDetailsService) {
		super();
		this.jwtUtils = jwtUtils;
		this.authFilter = authFilter;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
			AuthenticationManager authenticationManager) throws Exception {

		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
		jwtAuthenticationFilter.setFilterProcessesUrl(AppConfig.API_PATH + "/login");

		// @formatter:off
		return httpSecurity
				.csrf(config -> config.disable())
				.authorizeHttpRequests(auth -> 	auth.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		// @formatter:on
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
			throws Exception {
		// @formatter:off
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();
		// @formatter:on
	}
}
