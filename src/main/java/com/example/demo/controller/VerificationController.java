package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verify")
@Validated   // 🔴 REQUIRED for PathVariable & RequestHeader validation
public class VerificationController {

    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("/{verificationCode}")
    public VerificationLog verifyCertificate(
            @PathVariable
            @NotBlank(message = "Verification code must not be empty")
            String verificationCode,

            @RequestHeader("X-Client-IP")
            @NotBlank(message = "Client IP address is required")
            String ipAddress) {

        return verificationService.verifyCertificate(verificationCode, ipAddress);
    }

    @GetMapping("/logs/{certificateId}")
    public List<VerificationLog> getLogs(
            @PathVariable
            @NotBlank(message = "Certificate ID is required")
            Long certificateId) {

        return verificationService.getLogsByCertificate(certificateId);
    }
}
