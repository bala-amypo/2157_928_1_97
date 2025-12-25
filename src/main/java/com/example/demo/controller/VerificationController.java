package com.example.demo.controller;
import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/verify")
public class VerificationController {
    private final VerificationService verificationService;
    public VerificationController(VerificationService vs) { this.verificationService = vs; }

    @PostMapping("/{verificationCode}")
    public VerificationLog verify(@PathVariable String verificationCode, HttpServletRequest request) {
        return verificationService.verifyCertificate(verificationCode, request.getRemoteAddr());
    }

    @GetMapping("/logs/{certificateId}")
    public List<VerificationLog> logs(@PathVariable Long certificateId) {
        return verificationService.getLogsByCertificate(certificateId);
    }
}