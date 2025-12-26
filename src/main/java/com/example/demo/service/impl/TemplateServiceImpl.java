package com.example.demo.service.impl;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.service.TemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final CertificateTemplateRepository templateRepository;

    public TemplateServiceImpl(CertificateTemplateRepository templateRepository) {
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
    public CertificateTemplate getTemplateById(Long id) {
        return templateRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "CertificateTemplate not found with id: " + id));
    }
}
