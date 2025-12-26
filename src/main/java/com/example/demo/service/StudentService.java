package com.example.demo.service;

import com.example.demo.entity.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    Student getStudentByEmail(String email);

    List<Student> getAllStudents();
}
