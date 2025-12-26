package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ MUST
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
