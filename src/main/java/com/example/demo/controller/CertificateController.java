package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping("/generate")
    public Certificate generate(@RequestParam Long studentId,
                                @RequestParam Long templateId) {
        return certificateService.generateCertificate(studentId, templateId);
    }

    @GetMapping("/{id}")
    public Certificate getById(@PathVariable Long id) {
        return certificateService.getCertificate(id);
    }

    @GetMapping("/verify/{code}")
    public Certificate getByCode(@PathVariable String code) {
        return certificateService.findByVerificationCode(code);
    }

    @GetMapping("/student/{studentId}")
    public List<Certificate> getByStudent(@PathVariable Long studentId) {
        return certificateService.findByStudentId(studentId);
    }
}
