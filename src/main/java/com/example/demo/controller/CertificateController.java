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

    // POST /certificates/generate/{studentId}/{templateId}
    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generateCertificate(
            @PathVariable Long studentId,
            @PathVariable Long templateId) {

        return certificateService.generateCertificate(studentId, templateId);
    }

    // GET /certificates/{certificateId}
    @GetMapping("/{certificateId}")
    public Certificate getCertificate(@PathVariable Long certificateId) {
        return certificateService.getCertificateById(certificateId);
    }

    // GET /certificates/verify/code/{verificationCode}
    @GetMapping("/verify/code/{verificationCode}")
    public Certificate getByVerificationCode(@PathVariable String verificationCode) {
        return certificateService.getByVerificationCode(verificationCode);
    }
}
