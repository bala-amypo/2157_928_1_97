package com.example.demo.service.impl;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.service.TemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final CertificateTemplateRepository repo;

    public TemplateServiceImpl(CertificateTemplateRepository repo) {
        this.repo = repo;
    }

    @Override
    public CertificateTemplate addTemplate(CertificateTemplate template) {
        return repo.save(template);
    }

    @Override
    public List<CertificateTemplate> getAllTemplates() {
        return repo.findAll();
    }

    @Override
    public CertificateTemplate findById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}