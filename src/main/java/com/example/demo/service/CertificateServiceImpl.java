package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final CertificateTemplateRepository templateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository,
                                  StudentRepository studentRepository,
                                  CertificateTemplateRepository templateRepository) {
        this.certificateRepository = certificateRepository;
        this.studentRepository = studentRepository;
        this.templateRepository = templateRepository;
    }

    /**
     * WRITE transaction
     * If any exception occurs, certificate generation is rolled back
     */
    @Override
    @Transactional
    public Certificate generateCertificate(Long studentId, Long templateId) {

        // Fetch student (exception → rollback)
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // Fetch template (exception → rollback)
        CertificateTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate template not found"));

        // Create certificate
        Certificate certificate = new Certificate();
        certificate.setStudent(student);
        certificate.setTemplate(template);
        certificate.setIssuedDate(LocalDate.now());
        certificate.setVerificationCode(UUID.randomUUID().toString());
        certificate.setQrCodeUrl("QR-" + UUID.randomUUID());

        // Save certificate (commit only if no exception)
        return certificateRepository.save(certificate);
    }

    /**
     * READ-only transaction
     */
    @Override
    @Transactional(readOnly = true)
    public Certificate getCertificateById(Long certificateId) {
        return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    /**
     * READ-only transaction
     */
    @Override
    @Transactional(readOnly = true)
    public Certificate getByVerificationCode(String verificationCode) {
        return certificateRepository.findByVerificationCode(verificationCode)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    /**
     * READ-only transaction
     */
    @Override
    @Transactional(readOnly = true)
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }
}
