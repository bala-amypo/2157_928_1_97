package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final String secret = "mySecretKey"; // Replace with a secure secret in production
    private final long expirationMs = 24 * 60 * 60 * 1000; // 24 hours

    /**
     * Generate JWT token
     * @param claims Map of custom claims (like userId, role)
     * @param subject The subject (typically user's email)
     * @return JWT token string
     */
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Validate token
     * @param token JWT string
     * @return true if valid, false if expired or invalid
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Parse token and get claims
     * @param token JWT string
     * @return Claims object
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
