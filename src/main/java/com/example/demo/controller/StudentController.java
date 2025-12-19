package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Tag(name = "StudentController", description = "Student management APIs")
public class StudentController {

    private final StudentService studentService;

    // Add Student (POST first)
    @Operation(summary = "Add Student", description = "Create a new student")
    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // List All Students (GET next)
    @Operation(summary = "List All Students", description = "Get all students")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get Student by ID
    @Operation(summary = "Get Student by ID", description = "Fetch student by ID")
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
}
