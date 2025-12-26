package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;

import java.time.LocalDateTime;

public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    public VerificationServiceImpl(
            CertificateRepository certificateRepository,
            VerificationLogRepository logRepository) {
        this.certificateRepository = certificateRepository;
        this.logRepository = logRepository;
    }

    @Override
    public Certificate verify(String verificationCode, String ipAddress) {

        Certificate cert = certificateRepository.findByVerificationCode(verificationCode)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        VerificationLog log = new VerificationLog();
        log.setCertificate(cert);
        log.setVerifiedAt(LocalDateTime.now());
        log.setIpAddress(ipAddress);
        log.setStatus("SUCCESS");

        logRepository.save(log);

        return cert;
    }
}
