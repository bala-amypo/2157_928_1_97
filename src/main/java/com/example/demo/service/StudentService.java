package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

/**
 * StudentService defines the business logic for managing students.
 * <p>
 * Rules & Conditions from Test Case Helper / Image:
 * 1. When adding a student:
 *    - Check if email already exists.
 *    - Check if rollNumber already exists.
 *    - If duplicate exists, throw DuplicateResourceException with message containing "Student email exists".
 * 2. When fetching a student by ID:
 *    - If not found, throw ResourceNotFoundException with message containing "Student not found".
 * 3. All validation and business rules are implemented in the service layer.
 * 4. Controller only delegates to this service; no validation in controller.
 */
public interface StudentService {

    /**
     * Add a new student to the system.
     *
     * @param student Student entity to add
     * @return Saved Student entity
     * @throws DuplicateResourceException if email or roll number already exists
     */
    Student addStudent(Student student);

    /**
     * Get all students in the system.
     *
     * @return List of students
     */
    List<Student> getAllStudents();

    /**
     * Fetch a student by their ID.
     *
     * @param id Student ID
     * @return Student entity
     * @throws ResourceNotFoundException if student with given ID does not exist
     */
    Student getStudentById(Long id);
}
