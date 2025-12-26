package com.example.demo.controller;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/students")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService s) { this.service = s; }

    @PostMapping public ResponseEntity<Student> add(@RequestBody Student s) { return ResponseEntity.ok(service.addStudent(s)); }
    @GetMapping public ResponseEntity<List<Student>> list() { return ResponseEntity.ok(service.getAllStudents()); }
}