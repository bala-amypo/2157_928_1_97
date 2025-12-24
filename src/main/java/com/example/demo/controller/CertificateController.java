package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    // Generate certificate
    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generateCertificate(
            @PathVariable Long studentId,
            @PathVariable Long templateId) {

        return certificateService.generateCertificate(studentId, templateId);
    }

    // Get certificate by ID
    @GetMapping("/{certificateId}")
    public Certificate getCertificate(
            @PathVariable Long certificateId) {

        return certificateService.getCertificateById(certificateId);
    }

    // Verify certificate by code
    @GetMapping("/verify/code/{verificationCode}")
    public Certificate getByVerificationCode(
            @PathVariable String verificationCode) {

        return certificateService.getByVerificationCode(verificationCode);
    }
}
