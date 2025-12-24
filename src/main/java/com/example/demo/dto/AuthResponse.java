package com.example.demo.dto;

public class AuthResponse {

    private String token;

    // No-args constructor (Spring-ku thevai)
    public AuthResponse() {
    }

    // ONLY token constructor (unga error-ku fix)
    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
