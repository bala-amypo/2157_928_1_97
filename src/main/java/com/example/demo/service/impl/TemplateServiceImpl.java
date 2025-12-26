package com.example.demo.service.impl;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.repository.TemplateRepository;
import com.example.demo.service.TemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔥 WITHOUT THIS, ERROR WILL COME
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public CertificateTemplate saveTemplate(CertificateTemplate template) {
        return templateRepository.save(template);
    }

    @Override
    public List<CertificateTemplate> getAllTemplates() {
        return templateRepository.findAll();
    }

    @Override
    public CertificateTemplate getByName(String templateName) {
        return templateRepository.findByTemplateName(templateName)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }
}
