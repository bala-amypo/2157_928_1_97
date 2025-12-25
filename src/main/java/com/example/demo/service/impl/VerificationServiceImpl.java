package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {
        Certificate cert = certificateRepository.findByVerificationCode(verificationCode).orElse(null);
        String status = (cert != null) ? "SUCCESS" : "FAILED";

        VerificationLog log = VerificationLog.builder()
                .certificate(cert)
                .verifiedAt(LocalDateTime.now())
                .status(status)
                .ipAddress(clientIp)
                .build();

        return logRepository.save(log);
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        certificateRepository.findById(certificateId)
                .orElseThrow(()-> new RuntimeException("Certificate not found"));
        return logRepository.findByCertificateId(certificateId);
    }
}
