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

    public AuthController(UserService us, JwtUtil ju) { this.userService = us; this.jwtUtil = ju; }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest r) {
        User u = User.builder().name(r.getName()).email(r.getEmail()).password(r.getPassword()).role(r.getRole()).build();
        return ResponseEntity.ok(userService.register(u));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest r) {
        User u = userService.findByEmail(r.getEmail());
        if (u != null && encoder.matches(r.getPassword(), u.getPassword())) {
            String token = jwtUtil.generateToken(Map.of("role", u.getRole(), "userId", u.getId()), u.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, u.getId(), u.getEmail(), u.getRole()));
        }
        return ResponseEntity.status(401).build();
    }
}