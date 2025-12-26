package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;

import java.util.*;

public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certRepo;
    private final StudentRepository studentRepo;
    private final CertificateTemplateRepository templateRepo;

    public CertificateServiceImpl(CertificateRepository c, StudentRepository s, CertificateTemplateRepository t) {
        certRepo = c; studentRepo = s; templateRepo = t;
    }

    public Certificate generateCertificate(Long sid, Long tid) {
        Student s = studentRepo.findById(sid).orElseThrow();
        CertificateTemplate t = templateRepo.findById(tid).orElseThrow();

        Certificate c = Certificate.builder()
                .student(s)
                .template(t)
                .verificationCode("VC-" + UUID.randomUUID())
                .qrCodeUrl("data:image/png;base64,XXXX")
                .build();
        return certRepo.save(c);
    }

    public Certificate getCertificate(Long id) {
        return certRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Certificate not found"));
    }

    public Certificate findByVerificationCode(String code) {
        return certRepo.findByVerificationCode(code).orElseThrow(() ->
                new RuntimeException("Certificate not found"));
    }

    public List<Certificate> findByStudentId(Long id) {
        Student s = studentRepo.findById(id).orElseThrow();
        return certRepo.findByStudent(s);
    }
}
