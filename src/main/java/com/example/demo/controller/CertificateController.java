package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService service;

    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generate(@PathVariable Long studentId,
                                @PathVariable Long templateId) {
        return service.generateCertificate(studentId, templateId);
    }

    @GetMapping("/{id}")
    public Certificate get(@PathVariable Long id) {
        return service.getCertificate(id);
    }

    @GetMapping("/verify/code/{code}")
    public Certificate getByCode(@PathVariable String code) {
        return service.findByVerificationCode(code);
    }

    @GetMapping("/student/{studentId}")
    public List<Certificate> byStudent(@PathVariable Long studentId) {
        return service.findByStudentId(studentId);
    }
}
