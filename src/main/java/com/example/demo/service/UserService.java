package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    /**
     * Register a new user.
     * If role is null, it should default to "STAFF".
     * Password must be hashed before saving (handled in implementation).
     */
    User register(User user);

    /**
     * Find a user by email.
     * Throws RuntimeException if user not found (or handle in implementation).
     */
    User findByEmail(String email);
}

