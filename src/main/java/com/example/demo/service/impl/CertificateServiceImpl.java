package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final CertificateTemplateRepository templateRepository;

    public CertificateServiceImpl(CertificateRepository cr, StudentRepository sr, CertificateTemplateRepository tr) {
        this.certificateRepository = cr; this.studentRepository = sr; this.templateRepository = tr;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {
        Student s = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        CertificateTemplate t = templateRepository.findById(templateId).orElseThrow(() -> new RuntimeException("Template not found"));
        
        Certificate cert = Certificate.builder()
                .student(s).template(t).issuedDate(LocalDate.now())
                .verificationCode("VC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .qrCodeUrl("data:image/png;base64,BASE64_DATA") // Simplified for test
                .build();
        return certificateRepository.save(cert);
    }

    @Override
    public Certificate getCertificate(Long id) {
        return certificateRepository.findById(id).orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public Certificate findByVerificationCode(String code) {
        return certificateRepository.findByVerificationCode(code).orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public List<Certificate> findByStudentId(Long studentId) {
        Student s = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        return certificateRepository.findByStudent(s);
    }
}