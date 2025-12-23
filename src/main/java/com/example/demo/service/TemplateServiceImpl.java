package com.example.demo.service;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.repository.CertificateTemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final CertificateTemplateRepository templateRepository;

    public TemplateServiceImpl(CertificateTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * WRITE transaction
     * If duplicate template name is found, transaction is rolled back
     */
    @Override
    @Transactional
    public CertificateTemplate addTemplate(CertificateTemplate template) {

        // Check duplicate template name
        templateRepository.findByTemplateName(template.getTemplateName())
                .ifPresent(t -> {
                    throw new DuplicateResourceException("Template name already exists");
                });

        // Save template (commit only if no exception)
        return templateRepository.save(template);
    }

    /**
     * READ-only transaction
     */
    @Override
    @Transactional(readOnly = true)
    public List<CertificateTemplate> getAllTemplates() {
        return templateRepository.findAll();
    }
}
s