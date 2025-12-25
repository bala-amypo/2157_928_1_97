package com.example.demo.controller;
import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService; this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        User u = User.builder().name(req.getName()).email(req.getEmail()).password(req.getPassword()).role(req.getRole()).build();
        return ResponseEntity.ok(userService.register(u));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        User u = userService.findByEmail(req.getEmail());
        if (u != null && encoder.matches(req.getPassword(), u.getPassword())) {
            String token = jwtUtil.generateToken(Map.of("role", u.getRole(), "userId", u.getId()), u.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, u.getId(), u.getEmail(), u.getRole()));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}