package com.example.demo.controller;
import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/certificates")
public class CertificateController {
    private final CertificateService service;
    public CertificateController(CertificateService s) { this.service = s; }

    @PostMapping("/generate/{sId}/{tId}")
    public ResponseEntity<Certificate> generate(@PathVariable Long sId, @PathVariable Long tId) {
        return ResponseEntity.ok(service.generateCertificate(sId, tId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> get(@PathVariable Long id) { return ResponseEntity.ok(service.getCertificate(id)); }

    @GetMapping("/verify/code/{code}")
    public ResponseEntity<Certificate> verifyCode(@PathVariable String code) { return ResponseEntity.ok(service.findByVerificationCode(code)); }
}