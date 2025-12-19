package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
@Tag(name = "TemplateController", description = "Certificate template management APIs")
public class TemplateController {

    private final TemplateService templateService;

    // ✅ 1️⃣ ADD TEMPLATE (FIRST)
    @Operation(
        summary = "Add Template",
        description = "Create and save a new certificate template"
    )
    @PostMapping
    public CertificateTemplate addTemplate(
            @RequestBody CertificateTemplate template) {
        return templateService.addTemplate(template);
    }

    // ✅ 2️⃣ LIST ALL TEMPLATES (NEXT)
    @Operation(
        summary = "List All Templates",
        description = "Fetch all certificate templates"
    )
    @GetMapping
    public List<CertificateTemplate> getAllTemplates() {
        return templateService.getAllTemplates();
    }
}
