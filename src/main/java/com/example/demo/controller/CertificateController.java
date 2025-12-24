package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/generate/{studentId}/{templateId}")
    public ResponseEntity<Certificate> generate(@PathVariable Long studentId, @PathVariable Long templateId) {
        return ResponseEntity.ok(certificateService.generateCertificate(studentId, templateId));
    }

    @GetMapping("/{certificateId}")
    public ResponseEntity<Certificate> getCertificate(@PathVariable Long certificateId) {
        return ResponseEntity.ok(certificateService.getCertificate(certificateId));
    }

    @GetMapping("/verify/code/{verificationCode}")
    public ResponseEntity<Certificate> verifyByCode(@PathVariable String verificationCode) {
        return ResponseEntity.ok(certificateService.findByVerificationCode(verificationCode));
    }
}