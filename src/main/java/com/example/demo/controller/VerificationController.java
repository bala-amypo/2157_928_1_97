package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.VerificationService;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/verify")
public class VerificationController {

    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @GetMapping("/{code}")
    public Certificate verify(@PathVariable String code,
                              HttpServletRequest request) {

        String ip = request != null ? request.getRemoteAddr() : "0.0.0.0";
        return verificationService.verify(code, ip);
    }
}
