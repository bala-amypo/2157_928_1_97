package com.example.demo.security;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JwtUtil {
    private String secret;
    private Long expirationMs;

    public JwtUtil() { 
        this("abcdefghijklmnopqrstuvwxyz0123456789ABCD", 3600000L); 
    }

    // Required by automated tests
    public JwtUtil(String secret, Long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean validateToken(String token) {
        try { Jwts.parser().setSigningKey(secret).parseClaimsJws(token); return true; } 
        catch (Exception e) { return false; }
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }
}