package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService {
    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    public VerificationServiceImpl(CertificateRepository cr, VerificationLogRepository lr) {
        this.certificateRepository = cr; this.logRepository = lr;
    }

    @Override
    public VerificationLog verifyCertificate(String code, String ip) {
        Optional<Certificate> c = certificateRepository.findByVerificationCode(code);
        VerificationLog log = VerificationLog.builder()
                .certificate(c.orElse(null))
                .verifiedAt(LocalDateTime.now())
                .status(c.isPresent() ? "SUCCESS" : "FAILED")
                .ipAddress(ip).build();
        return logRepository.save(log);
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long id) { return List.of(); }
}