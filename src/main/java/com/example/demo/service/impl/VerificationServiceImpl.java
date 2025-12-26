package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certificateRepository;

    @Autowired
    public VerificationServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public Certificate verify(String verificationCode) {
        return certificateRepository.findByVerificationCode(verificationCode)
                .orElseThrow(() -> new RuntimeException("Certificate not found with code: " + verificationCode));
    }
}
