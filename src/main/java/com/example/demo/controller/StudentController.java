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

    // ✅ 1️⃣ ADD STUDENT (FIRST)
    @PostMapping
    @Operation(
            summary = "Add Student",
            description = "Create and save a new student"
    )
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // ✅ 2️⃣ LIST ALL STUDENTS (NEXT)
    @GetMapping
    @Operation(
            summary = "List All Students",
            description = "Fetch all students"
    )
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
