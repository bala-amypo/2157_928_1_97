package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * WRITE transaction
     * If any RuntimeException occurs, data will be rolled back
     */
    @Override
    @Transactional
    public User register(User user) {

        // Step 1: Save user
        userRepository.save(user);

        // Step 2: Condition to test transaction rollback
        if (user.getEmail() != null && user.getEmail().endsWith("@test.com")) {
            throw new ResourceNotFoundException("Transaction rollback test");
        }

        // Step 3: Commit happens only if no exception
        return user;
    }

    /**
     * READ-only transaction
     */
    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
