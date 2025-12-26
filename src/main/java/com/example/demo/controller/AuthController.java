package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User u = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
        return ResponseEntity.ok(userService.register(u));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            User user = userService.findByEmail(request.getEmail());
            if (!new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().matches(request.getPassword(), user.getPassword()))
                return ResponseEntity.status(401).build();

            String token = jwtUtil.generateToken(
                    Map.of("userId", user.getId(), "email", user.getEmail(), "role", user.getRole()),
                    user.getEmail());

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}
