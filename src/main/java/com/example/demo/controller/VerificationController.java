package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verify")
public class VerificationController {

    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    // Verify certificate using verification code
    @PostMapping("/{verificationCode}")
    public VerificationLog verifyCertificate(
            @PathVariable String verificationCode,
            HttpServletRequest request) {

        // Automatically get client IP address
        String ipAddress = request.getRemoteAddr();

        return verificationService.verifyCertificate(verificationCode, ipAddress);
    }

    // Get verification logs by certificate ID
    @GetMapping("/logs/{certificateId}")
    public List<VerificationLog> getLogs(
            @PathVariable Long certificateId) {

        return verificationService.getLogsByCertificate(certificateId);
    }
}
