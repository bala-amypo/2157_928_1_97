package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicateResourceException;
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
     * If duplicate is found, exception is thrown and save is rolled back
     */
    @Override
    @Transactional
    public Student addStudent(Student student) {

        // Check for duplicate roll number
        studentRepository.findByRollNumber(student.getRollNumber())
                .ifPresent(s -> {
                    throw new DuplicateResourceException("Student roll number already exists");
                });

        // Save student (commit happens only if no exception)
        return studentRepository.save(student);
    }

    /**
     * READ-only transaction
     */
    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * READ-only transaction
     */
    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }
}
