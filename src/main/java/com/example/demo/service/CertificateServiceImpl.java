package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {
        // Your logic to create and save a certificate
        Certificate certificate = new Certificate();
        certificate.setStudentId(studentId);
        certificate.setTemplateId(templateId);
        certificate.setVerificationCode("CODE" + System.currentTimeMillis()); // Example
        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate getCertificateById(Long certificateId) {
        return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public Certificate getByVerificationCode(String verificationCode) {
        return certificateRepository.findByVerificationCode(verificationCode)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }
}
