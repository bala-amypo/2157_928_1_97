package com.example.demo.controller;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/templates")
public class TemplateController {
    private final TemplateService service;
    public TemplateController(TemplateService s) { this.service = s; }

    @PostMapping public ResponseEntity<CertificateTemplate> add(@RequestBody CertificateTemplate t) { return ResponseEntity.ok(service.addTemplate(t)); }
    @GetMapping public ResponseEntity<List<CertificateTemplate>> list() { return ResponseEntity.ok(service.getAllTemplates()); }
}