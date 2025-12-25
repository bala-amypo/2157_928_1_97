package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
@Tag(name = "Certificates")
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
}