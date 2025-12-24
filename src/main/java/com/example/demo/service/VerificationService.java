// VerificationService.java
package com.example.demo.service;
import com.example.demo.entity.VerificationLog;
import java.util.List;

public interface VerificationService {
    VerificationLog verifyCertificate(String verificationCode, String clientIp);
    List<VerificationLog> getLogsByCertificate(Long certificateId);
}

// VerificationServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VerificationServiceImpl implements VerificationService {
    private final VerificationLogRepository logRepository;
    private final CertificateRepository certificateRepository;

    public VerificationServiceImpl(VerificationLogRepository logRepository, CertificateRepository certificateRepository) {
        this.logRepository = logRepository;
        this.certificateRepository = certificateRepository;
    }

    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {
        Optional<Certificate> certOpt = certificateRepository.findByVerificationCode(verificationCode);
        
        VerificationLog log = VerificationLog.builder()
                .certificate(certOpt.orElse(null))
                .verifiedAt(LocalDateTime.now())
                .ipAddress(clientIp)
                .status(certOpt.isPresent() ? "SUCCESS" : "FAILED")
                .build();

        return logRepository.save(log);
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        Certificate cert = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
        
        // Assuming findByCertificate is a standard query or we filter all logs
        return logRepository.findAll().stream()
                .filter(l -> l.getCertificate() != null && l.getCertificate().getId().equals(certificateId))
                .collect(Collectors.toList());
    }
}