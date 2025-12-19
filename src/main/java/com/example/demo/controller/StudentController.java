package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Tag(name = "StudentController", description = "Student management APIs")
public class StudentController {

    private final StudentService studentService;

    // ✅ SHOWS: Add Student
    @Operation(
        summary = "Add Student",
        description = "Create and save a new student"
    )
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // ✅ SHOWS: List All Students
    @Operation(
        summary = "List All Students",
        description = "Fetch all students from database"
    )
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
