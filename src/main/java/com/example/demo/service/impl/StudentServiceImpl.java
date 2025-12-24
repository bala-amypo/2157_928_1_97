package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * WRITE transaction
     * Checks for duplicate email and roll number before saving.
     * Throws RuntimeException if duplicate exists.
     */
    @Override
    @Transactional
    public Student addStudent(Student student) {

        // Check for duplicate email
        studentRepository.findByEmail(student.getEmail())
                .ifPresent(s -> {
                    throw new RuntimeException("Student email exists");
                });

        // Check for duplicate roll number
        studentRepository.findByRollNumber(student.getRollNumber())
                .ifPresent(s -> {
                    throw new RuntimeException("Student roll number already exists");
                });

        // Save student (commit only if no exception)
        return studentRepository.save(student);
    }

    /**
     * READ-only transaction: Get all students
     */
    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * READ-only transaction: Get student by ID
     * Throws ResourceNotFoundException if not found
     */
    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }
}
