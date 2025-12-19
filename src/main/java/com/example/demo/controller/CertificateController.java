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
@Tag(name = "CertificateController", description = "Certificate management APIs")
public class CertificateController {

    private final CertificateService certificateService;

    // 1️⃣ POST /generate/{studentId}/{templateId}
    @Operation(
        summary = "Create Certificate",
        description = "Generate certificate for a student using template"
    )
    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generateCertificate(
            @PathVariable Long studentId,
            @PathVariable Long templateId) {

        return certificateService.generateCertificate(studentId, templateId);
    }

    // 2️⃣ GET /{certificateId}
    @Operation(
        summary = "Get Certificate Details",
        description = "Fetch certificate details by certificate ID"
    )
    @GetMapping("/{certificateId}")
    public Certificate getCertificateById(
            @PathVariable Long certificateId) {

        return certificateService.getCertificateById(certificateId);
    }

    // 3️⃣ GET /verify/code/{verificationCode}
    @Operation(
        summary = "Fetch Certificate By Verification Code",
        description = "Get certificate using verification code"
    )
    @GetMapping("/verify/code/{verificationCode}")
    public Certificate getCertificateByVerificationCode(
            @PathVariable String verificationCode) {

        return certificateService.getByVerificationCode(verificationCode);
    }
}
