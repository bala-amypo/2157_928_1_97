package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceImpl implements VerificationService {
    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    public VerificationServiceImpl(CertificateRepository certificateRepository,
                                 VerificationLogRepository logRepository) {
        this.certificateRepository = certificateRepository;
        this.logRepository = logRepository;
    }

    @Override
    public Certificate verifyCertificate(String verificationCode) {
        Certificate certificate = certificateRepository.findByVerificationCode(verificationCode)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        VerificationLog log = VerificationLog.builder()
                .verificationCode(verificationCode)
                .status("SUCCESS")
                .ipAddress("127.0.0.1")
                .build();
        logRepository.save(log);

        return certificate;
    }
}