package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService,
                          JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setRole(req.getRole());

        userService.register(user);
        return ResponseEntity.ok("User registered");
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        String token = jwtUtil.generateToken(user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setToken(token);

        return ResponseEntity.ok(response);
    }
}
