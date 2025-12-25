package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional; // <--- ADD THIS LINE

@Service
public class VerificationServiceImpl implements VerificationService {
    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    // Constructor Injection (Constraint #2)
    public VerificationServiceImpl(CertificateRepository certificateRepository, VerificationLogRepository logRepository) {
        this.certificateRepository = certificateRepository;
        this.logRepository = logRepository;
    }

    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {
        Optional<Certificate> cert = certificateRepository.findByVerificationCode(verificationCode);
        
        VerificationLog log = VerificationLog.builder()
                .certificate(cert.orElse(null))
                .verifiedAt(LocalDateTime.now())
                .status(cert.isPresent() ? "SUCCESS" : "FAILED")
                .ipAddress(clientIp)
                .build();
        return logRepository.save(log);
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        Certificate cert = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
        return logRepository.findByCertificate(cert);
    }
}