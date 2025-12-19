package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {

        Certificate certificate =
                certificateRepository.findByVerificationCode(verificationCode).orElse(null);

        VerificationLog log = VerificationLog.builder()
                .verificationCode(verificationCode)
                .clientIp(clientIp)
                .certificate(certificate)
                .valid(certificate != null)
                .verificationTime(LocalDateTime.now())
                .build();

        return logRepository.save(log);
    }

    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        return logRepository.findByCertificateId(certificateId);
    }
}
