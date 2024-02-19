package com.pruebatecnica.identity.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilities {

	private static final Logger log = LoggerFactory.getLogger(JwtUtilities.class);

	private String secretKey;
	private String timeExpiration;

	public JwtUtilities(@Value("${jwt.secret.key}") String secretKey,
			@Value("${jwt.time.expiration}") String timeExpiration) {

		this.secretKey = secretKey;
		this.timeExpiration = timeExpiration;
	}

	public String generateAccesToken(String username) {
		// @formatter:off
				return Jwts.builder()
						.setSubject(username)
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
						.signWith(getSignatureKey(), SignatureAlgorithm.HS256)
						.compact(); 
		// @formatter:on
	}

	public boolean isTokenValid(String token) {
		// @formatter:off
		try {
			Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			log.error("Token invalido, error: ".concat(e.getMessage()));
			return false;
		}
		// @formatter:on
	}

	public Key getSignatureKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
		return claimsTFunction.apply(extractClaims(token));
	}

	public Claims extractClaims(String token) {
		// @formatter:off
		return Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody(); 
		// @formatter:on
	}

	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}
}
