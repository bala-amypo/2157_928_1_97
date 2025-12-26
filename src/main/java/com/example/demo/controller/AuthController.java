package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class AuthController {
    private final UserService svc;
    private final JwtUtil jwt;

    public AuthController(UserService s, JwtUtil j) {
        svc = s; jwt = j;
    }

    public ResponseEntity<?> register(RegisterRequest r) {
        User u = svc.register(User.builder()
                .name(r.getName()).email(r.getEmail())
                .password(r.getPassword()).role(r.getRole()).build());
        return ResponseEntity.ok(u);
    }

    public ResponseEntity<?> login(AuthRequest r) {
        try {
            User u = svc.findByEmail(r.getEmail());
            String t = jwt.generateToken(
                    Map.of("email", u.getEmail(), "role", u.getRole()),
                    u.getEmail());
            return ResponseEntity.ok(new AuthResponse(t));
        } catch(Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}
