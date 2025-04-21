package com.chatop.chaTop.service;

import com.chatop.chaTop.model.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Service class for managing the token generation.
 */
@Service
public class JWTService {
    private final JwtEncoder jwtEncoder;


    public JWTService(final JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    /**
     * Generate a token from user details.
     *
     * @param user The requested login user.
     * @return A generated Token.
     */
    public String generateToken(final User user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now).expiresAt(now.plus(1, ChronoUnit.DAYS)).subject(String.valueOf(user.getId())).build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

}
