package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify")
@Tag(name = "Verification")
public class VerificationController {

    private final VerificationService service;

    public VerificationController(VerificationService service) {
        this.service = service;
    }

    @PostMapping("/{code}")
    public VerificationLog verify(@PathVariable String code,
                                  HttpServletRequest request) {
        return service.verifyCertificate(code, request.getRemoteAddr());
    }
}