package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    /**
     * Register a new user.
     * - If role is null, it should default to "STAFF".
     * - Password hashing should be handled in implementation.
     */
    User register(User user);

    /**
     * Find a user by email.
     * - Throws ResourceNotFoundException if user not found.
     */
    User findByEmail(String email);
}
