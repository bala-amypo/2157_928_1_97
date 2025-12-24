package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> registerRequest) {
        // Extract request data
        String fullName = registerRequest.get("fullName"); // Match field in User entity
        String email = registerRequest.get("email");
        String password = registerRequest.get("password");

        // Encode password
        String encodedPassword = passwordEncoder.encode(password);

        // Create user using builder
        User user = User.builder()
                .fullName(fullName)   // <-- make sure User.java has "fullName"
                .email(email)
                .password(encodedPassword)
                .role("STAFF")        // default role
                .build();

        // Save user
        userService.register(user);

        // Prepare JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        String token = jwtUtil.generateToken(claims, user.getEmail());

        // Return response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "User registered successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        User user = userService.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        String token = jwtUtil.generateToken(claims, user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "Login successful");

        return ResponseEntity.ok(response);
    }
}
