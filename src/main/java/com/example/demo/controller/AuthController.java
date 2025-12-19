package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AuthController", description = "Authentication APIs")
public class AuthController {

    private final AuthService authService;

    // POST /register
    @Operation(summary = "Register User", description = "Create a new user", method = "POST")
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    // POST /login
    @Operation(summary = "Login User", description = "Authenticate user", method = "POST")
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return authService.login(user);
    }
}
