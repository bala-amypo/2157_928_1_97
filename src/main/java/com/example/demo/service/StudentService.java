package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

/**
 * StudentService defines the business logic for managing students.
 * 
 * Rules from Test Case Helper / Image:
 * 1. When adding a student:
 *    - Check if email or rollNumber already exists.
 *    - If duplicate exists, throw ResourceNotFoundException with message containing "Student email exists".
 * 2. When fetching a student by ID:
 *    - If not found, throw ResourceNotFoundException with message containing "Student not found".
 * 3. All validation and business rules are implemented in the service layer.
 * 4. Controller only delegates to this service; no validation in controller.
 */
public interface StudentService {

    Student addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);
}
