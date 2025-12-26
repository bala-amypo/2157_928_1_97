package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.service.CertificateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // ⭐ VERY IMPORTANT
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public Certificate save(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public List<Certificate> findAll() {
        return certificateRepository.findAll();
    }

    @Override
    public Certificate findById(Long id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }
}
