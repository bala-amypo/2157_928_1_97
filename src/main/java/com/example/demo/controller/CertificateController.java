package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
@Tag(name = "CertificateController", description = "Certificate APIs")
public class CertificateController {

    private final CertificateService certificateService;

    // ✅ POST /certificates/generate/{studentId}/{templateId}
    @Operation(
            summary = "Generate Certificate",
            description = "Create certificate for student using template"
    )
    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generateCertificate(
            @PathVariable Long studentId,
            @PathVariable Long templateId) {

        return certificateService.generateCertificate(studentId, templateId);
    }

    // ✅ GET /certificates/{certificateId}
    @Operation(
            summary = "Get Certificate",
            description = "Get certificate details by ID"
    )
    @GetMapping("/{certificateId}")
    public Certificate getCertificateById(
            @PathVariable Long certificateId) {

        return certificateService.getCertificateById(certificateId);
    }

    // ✅ GET /certificates/verify/code/{verificationCode}
    @Operation(
            summary = "Verify Certificate",
            description = "Verify certificate using verification code"
    )
    @GetMapping("/verify/code/{verificationCode}")
    public Certificate getByVerificationCode(
            @PathVariable String verificationCode) {

        return certificateService.getByVerificationCode(verificationCode);
    }
}
