package com.example.demo.repository;

import com.example.demo.entity.CertificateTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Long> {

    /**
     * Find a certificate template by its unique template name.
     * This is useful to avoid duplicate templates.
     *
     * @param templateName the name of the template
     * @return Optional containing the CertificateTemplate if found
     */
    Optional<CertificateTemplate> findByTemplateName(String templateName);
}
