package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final CertificateTemplateRepository templateRepository;

    public CertificateServiceImpl(CertificateRepository cr, StudentRepository sr, CertificateTemplateRepository tr) {
        this.certificateRepository = cr;
        this.studentRepository = sr;
        this.templateRepository = tr;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        CertificateTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found"));

        String vCode = "VC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String qrCodeUrl = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA..." + vCode;

        Certificate certificate = Certificate.builder()
                .student(student)
                .template(template)
                .issuedDate(LocalDate.now())
                .verificationCode(vCode)
                .qrCodeUrl(qrCodeUrl)
                .build();

        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate getCertificate(Long certificateId) {
        return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    @Override
    public Certificate findByVerificationCode(String code) {
        return certificateRepository.findByVerificationCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    @Override
    public List<Certificate> findByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return certificateRepository.findByStudent(student);
    }
}