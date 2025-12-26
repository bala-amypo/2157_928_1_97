package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;
import com.example.demo.exception.ResourceNotFoundException;
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
        Student s = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        CertificateTemplate t = templateRepository.findById(templateId).orElseThrow(() -> new RuntimeException("Template not found"));
        
        String vCode = "VC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Certificate c = Certificate.builder()
                .student(s).template(t).issuedDate(LocalDate.now())
                .verificationCode(vCode)
                .qrCodeUrl("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgA...") // Mock Base64
                .build();
        return certificateRepository.save(c);
    }

    @Override
    public Certificate getCertificate(Long id) {
        return certificateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    @Override
    public Certificate findByVerificationCode(String code) {
        return certificateRepository.findByVerificationCode(code).orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    @Override
    public List<Certificate> findByStudentId(Long studentId) {
        Student s = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return certificateRepository.findByStudent(s);
    }
}