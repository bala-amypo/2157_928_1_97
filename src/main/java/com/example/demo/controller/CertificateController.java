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
@Tag(name = "CertificateController", description = "Certificate generation & retrieval APIs")
public class CertificateController {

    private final CertificateService certificateService;

    // ✅ 1️⃣ CREATE CERTIFICATE
    // POST /certificates/generate/{studentId}/{templateId}
    @Operation(
            summary = "Generate Certificate",
            description = "Create certificate using studentId and templateId"
    )
    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generateCertificate(
            @PathVariable Long studentId,
            @PathVariable Long templateId
    ) {
        return certificateService.generateCertificate(studentId, templateId);
    }

    // ✅ 2️⃣ GET CERTIFICATE DETAILS
    // GET /certificates/{certificateId}
    @Operation(
            summary = "Get Certificate Details",
            description = "Fetch certificate by certificateId"
    )
    @GetMapping("/{certificateId}")
    public Certificate getCertificateById(
            @PathVariable Long certificateId
    ) {
        return certificateService.getCertificateById(certificateId);
    }

    // ✅ 3️⃣ VERIFY CERTIFICATE BY CODE
    // GET /certificates/verify/code/{verificationCode}
    @Operation(
            summary = "Verify Certificate",
            description = "Fetch certificate using verification code"
    )
    @GetMapping("/verify/code/{verificationCode}")
    public Certificate verifyByCode(
            @PathVariable String verificationCode
    ) {
        return certificateService.getByVerificationCode(verificationCode);
    }
}
