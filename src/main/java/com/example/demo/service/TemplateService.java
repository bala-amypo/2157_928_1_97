package com.example.demo.service;

import com.example.demo.entity.CertificateTemplate;
import java.util.List;

public interface TemplateService {

    CertificateTemplate saveTemplate(CertificateTemplate template);

    List<CertificateTemplate> getAllTemplates();

    CertificateTemplate getByName(String templateName);
}
