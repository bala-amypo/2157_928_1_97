package com.example.demo.controller;
import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/verify")
public class VerificationController {
    private final VerificationService service;
    public VerificationController(VerificationService s) { this.service = s; }

    @PostMapping("/{code}")
    public ResponseEntity<VerificationLog> verify(@PathVariable String code) { return ResponseEntity.ok(service.verifyCertificate(code, "127.0.0.1")); }
}