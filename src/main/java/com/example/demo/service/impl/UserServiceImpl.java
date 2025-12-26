package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public User register(User u) {
        if (repo.findByEmail(u.getEmail()).isPresent())
            throw new RuntimeException("User email exists");
        if (u.getRole() == null) u.setRole("STAFF");
        return repo.save(u);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow();
    }
}
