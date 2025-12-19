package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {
        Certificate certificate = Certificate.builder()
                .studentId(studentId)
                .templateId(templateId)
                .verificationCode(UUID.randomUUID().toString())
                .build();
        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate getCertificateById(Long id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with id: " + id));
    }

    @Override
    public Certificate getByVerificationCode(String code) {
        return certificateRepository.findByVerificationCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with verification code: " + code));
    }
}
