package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;

public class JwtUtil {
    private final String secret;
    private final long exp;

    public JwtUtil(String s, long e) { secret = s; exp = e; }

    public String generateToken(Map<String,Object> claims, String sub) {
        return Jwts.builder().setClaims(claims).setSubject(sub)
                .setExpiration(new Date(System.currentTimeMillis()+exp))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean validateToken(String t) {
        try { Jwts.parser().setSigningKey(secret).parseClaimsJws(t); return true; }
        catch(Exception e){ return false; }
    }

    public Jws<Claims> parseToken(String t) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(t);
    }
}
