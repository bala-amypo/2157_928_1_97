package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService service;

    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public Certificate generate(@RequestParam Long studentId,
                                @RequestParam Long templateId) {
        return service.generateCertificate(studentId, templateId);
    }

    @GetMapping("/{id}")
    public Certificate get(@PathVariable Long id) {
        return service.getCertificate(id);
    }
}
