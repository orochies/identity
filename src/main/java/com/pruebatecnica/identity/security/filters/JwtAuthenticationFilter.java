package com.pruebatecnica.identity.security.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.identity.config.AppException;
import com.pruebatecnica.identity.core.UserIdentified;
import com.pruebatecnica.identity.security.jwt.JwtUtilities;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private JwtUtilities jwtUtils;

	public JwtAuthenticationFilter(JwtUtilities jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		UserIdentified user = null;
		String username = "";
		String password = "";

		try {
			user = new ObjectMapper().readValue(request.getInputStream(), UserIdentified.class);
			username = user.getUsername();
			password = user.getPassword();

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
					password);

			return getAuthenticationManager().authenticate(authToken);
		} catch (IOException e) {
			throw new AppException("No se pudo extraer la informacion de usuario.", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		User user = (User) authResult.getPrincipal();
		String token = jwtUtils.generateAccesToken(user.getUsername());

		response.addHeader("Authorization", token);

		Map<String, Object> httpResponse = new HashMap<>();
		httpResponse.put("token", token);
		httpResponse.put("Message", "Autenticacion Correcta");
		httpResponse.put("Username", user.getUsername());

		response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().flush();

		super.successfulAuthentication(request, response, chain, authResult);
	}
}
