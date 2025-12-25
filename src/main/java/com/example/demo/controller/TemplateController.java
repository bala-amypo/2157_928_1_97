package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    public CertificateTemplate addTemplate(@RequestBody CertificateTemplate template){
        return templateService.addTemplate(template);
    }

    @GetMapping
    public List<CertificateTemplate> getAllTemplates(){
        return templateService.getAllTemplates();
    }
}
