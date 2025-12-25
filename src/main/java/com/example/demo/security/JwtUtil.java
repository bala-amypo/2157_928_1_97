package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JwtUtil {
    private final String secret;
    private final Long expirationMs;

    // Required by Spring for normal execution
    public JwtUtil() {
        this.secret = "abcdefghijklmnopqrstuvwxyz0123456789ABCD";
        this.expirationMs = 3600000L;
    }

    // Required by Technical Constraint #4 for Automated Tests
    public JwtUtil(String secret, Long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public boolean validateToken(String token) {
        try { Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token); return true; }
        catch (Exception e) { return false; }
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);
    }
}