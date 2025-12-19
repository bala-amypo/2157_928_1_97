package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {

        Certificate certificate =
                certificateRepository.findByVerificationCode(verificationCode).orElse(null);

        VerificationLog log = new VerificationLog();
        log.setCertificate(certificate);
        log.setVerifiedAt(LocalDateTime.now());
        log.setIpAddress(clientIp);
        log.setStatus(certificate != null ? "SUCCESS" : "FAILED");

        return logRepository.save(log);
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        return logRepository.findByCertificateId(certificateId);
    }
}
