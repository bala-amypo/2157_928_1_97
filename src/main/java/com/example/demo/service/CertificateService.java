package com.example.demo.service;

import com.example.demo.entity.Certificate;
import java.util.List;

public interface CertificateService {

    /**
     * Generate a certificate for a student using a template.
     * Throws ResourceNotFoundException if student or template not found.
     */
    Certificate generateCertificate(Long studentId, Long templateId);

    /**
     * Fetch a certificate by its ID.
     * Throws ResourceNotFoundException if certificate not found.
     */
    Certificate getCertificateById(Long certificateId);

    /**
     * Fetch a certificate by its verification code.
     * Throws ResourceNotFoundException if certificate not found.
     */
    Certificate getByVerificationCode(String verificationCode);

    /**
     * Get all certificates.
     */
    List<Certificate> getAllCertificates();
}
