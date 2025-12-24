package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their email.
     * Used for login, registration, and validation checks.
     *
     * @param email the email of the user
     * @return Optional containing the User if found
     */
    Optional<User> findByEmail(String email);
}
