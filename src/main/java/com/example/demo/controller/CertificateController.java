package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
@Validated
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generateCertificate(
            @PathVariable @NotNull(message = "Student ID is required") Long studentId,
            @PathVariable @NotNull(message = "Template ID is required") Long templateId) {

        return certificateService.generateCertificate(studentId, templateId);
    }

    @GetMapping("/{certificateId}")
    public Certificate getCertificate(
            @PathVariable @NotNull(message = "Certificate ID is required") Long certificateId) {

        return certificateService.getCertificateById(certificateId);
    }

    @GetMapping("/verify/code/{verificationCode}")
    public Certificate getByVerificationCode(
            @PathVariable @NotBlank(message = "Verification code is required") String verificationCode) {

        return certificateService.getByVerificationCode(verificationCode);
    }
}
