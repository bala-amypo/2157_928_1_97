package com.example.demo.service;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.repository.CertificateTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final CertificateTemplateRepository repository;

    public CertificateTemplate addTemplate(CertificateTemplate template) {
        if (repository.findByName(template.getName()).isPresent()) {
            return null;
        }
        return repository.save(template);
    }

    public List<CertificateTemplate> getAllTemplates() {
        return repository.findAll();
    }

    public CertificateTemplate findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
