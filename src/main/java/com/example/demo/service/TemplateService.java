package com.example.demo.service;

import com.example.demo.entity.CertificateTemplate;
import java.util.List;

/**
 * TemplateService defines the business logic for managing certificate templates.
 *
 * Rules & Conditions from Test Case Helper / Image:
 * 1. When adding a template:
 *    - Check if templateName already exists.
 *    - If duplicate exists, throw RuntimeException with message containing "Template name exists".
 *    - Validate backgroundUrl (must not be blank).
 * 2. getAllTemplates returns all templates.
 * 3. All validation and business rules are implemented in the service layer.
 * 4. Controller only delegates to this service; no validation in controller.
 */
public interface TemplateService {

    /**
     * Add a new certificate template.
     *
     * @param template CertificateTemplate entity to add
     * @return Saved CertificateTemplate entity
     * @throws RuntimeException if templateName already exists or backgroundUrl is invalid
     */
    CertificateTemplate addTemplate(CertificateTemplate template);

    /**
     * Get all certificate templates.
     *
     * @return List of CertificateTemplate entities
     */
    List<CertificateTemplate> getAllTemplates();
}
