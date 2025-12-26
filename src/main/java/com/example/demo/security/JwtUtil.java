package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private final String secret;
    private final long expiration;

    public JwtUtil() {
        this.secret = "defaultsecretkey1234567890";
        this.expiration = 3600000;
    }

    public JwtUtil(String secret, long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }
}
