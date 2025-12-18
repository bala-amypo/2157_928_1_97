package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final CertificateTemplateRepository templateRepository;

    public Certificate generateCertificate(Long studentId, Long templateId) {

        Student student = studentRepository.findById(studentId).orElse(null);
        CertificateTemplate template = templateRepository.findById(templateId).orElse(null);

        if (student == null || template == null) {
            return null;
        }

        Certificate certificate = Certificate.builder()
                .student(student)
                .template(template)
                .verificationCode(UUID.randomUUID().toString())
                .issueDate(LocalDateTime.now())
                .build();

        return certificateRepository.save(certificate);
    }

    public Certificate getCertificate(Long certificateId) {
        return certificateRepository.findById(certificateId).orElse(null);
    }

    public Certificate findByVerificationCode(String code) {
        return certificateRepository.findByVerificationCode(code).orElse(null);
    }

    public List<Certificate> findByStudentId(Long studentId) {
        return certificateRepository.findByStudentId(studentId);
    }
}
