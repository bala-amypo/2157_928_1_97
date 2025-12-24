package com.example.demo.service;

import com.example.demo.entity.Student;
import java.util.List;

public interface StudentService {

    /**
     * Add a new student.
     * Throws DuplicateResourceException if a student with the same email or roll number exists.
     */
    Student addStudent(Student student);

    /**
     * Get all students.
     */
    List<Student> getAllStudents();

    /**
     * Fetch a student by ID.
     * Throws ResourceNotFoundException if student not found.
     */
    Student getStudentById(Long id);
}
