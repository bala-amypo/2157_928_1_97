package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certRepo;
    private final StudentRepository studentRepo;
    private final CertificateTemplateRepository templateRepo;

    public CertificateServiceImpl(CertificateRepository certRepo,
                                  StudentRepository studentRepo,
                                  CertificateTemplateRepository templateRepo) {
        this.certRepo = certRepo;
        this.studentRepo = studentRepo;
        this.templateRepo = templateRepo;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {
        Certificate cert = Certificate.builder()
                .student(studentRepo.findById(studentId).orElseThrow())
                .template(templateRepo.findById(templateId).orElseThrow())
                .issuedDate(LocalDate.now())
                .verificationCode(UUID.randomUUID().toString())
                .qrCodeUrl("QR")
                .build();
        return certRepo.save(cert);
    }

    @Override
    public Certificate getCertificate(Long id) {
        return certRepo.findById(id).orElseThrow();
    }

    @Override
    public Certificate findByVerificationCode(String code) {
        return certRepo.findByVerificationCode(code).orElseThrow();
    }

    @Override
    public List<Certificate> findByStudentId(Long studentId) {
        return certRepo.findByStudent(
                studentRepo.findById(studentId).orElseThrow()
        );
    }
}