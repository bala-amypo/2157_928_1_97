package com.example.demo.service;

import com.example.demo.entity.Certificate;

public interface CertificateService {
    Certificate generateCertificate(Long studentId, Long templateId);
    Certificate getCertificateById(Long id);
    Certificate getByVerificationCode(String code);
}
