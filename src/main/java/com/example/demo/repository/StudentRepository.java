package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find a student by their unique roll number.
     * Useful to prevent duplicate student entries.
     *
     * @param rollNumber the roll number of the student
     * @return Optional containing the Student if found
     */
    Optional<Student> findByRollNumber(String rollNumber);

    /**
     * Find a student by their unique email.
     * This helps in authentication and registration checks.
     *
     * @param email the email of the student
     * @return Optional containing the Student if found
     */
    Optional<Student> findByEmail(String email);
}
