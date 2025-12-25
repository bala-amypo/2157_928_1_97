package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestParam Long studentId, @RequestParam Long templateId) {
        try {
            Certificate certificate = certificateService.generateCertificate(studentId, templateId);
            return ResponseEntity.ok(certificate);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            Certificate certificate = certificateService.getCertificate(id);
            return ResponseEntity.ok(certificate);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}