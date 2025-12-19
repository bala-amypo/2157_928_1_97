package com.example.demo.service;

import com.example.demo.entity.Certificate;
import java.util.List;

public interface CertificateService {

    Certificate generateCertificate(Long studentId, Long templateId);

    Certificate getCertificateById(Long certificateId);

    Certificate getByVerificationCode(String verificationCode);

    // 🔥 ADD THIS METHOD
    List<Certificate> getAllCertificates();
}
