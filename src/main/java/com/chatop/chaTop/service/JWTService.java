package com.chatop.chaTop.service;

import com.chatop.chaTop.model.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JWTService {
	private final JwtEncoder jwtEncoder;


	public JWTService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}

	public String generateToken(User user) {
		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now).expiresAt(now.plus(1, ChronoUnit.DAYS)).subject(String.valueOf(user.getId())).build();
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(claims);
		return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	}

}
