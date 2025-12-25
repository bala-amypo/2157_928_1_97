package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verify")
public class VerificationController {
    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @GetMapping("/{verificationCode}")
    public ResponseEntity<?> verify(@PathVariable String verificationCode) {
        try {
            Certificate certificate = verificationService.verifyCertificate(verificationCode);
            return ResponseEntity.ok(certificate);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}