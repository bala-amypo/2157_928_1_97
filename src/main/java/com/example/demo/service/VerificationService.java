package com.example.demo.service;

import com.example.demo.entity.Certificate;

public interface VerificationService {
    Certificate verifyCertificate(String verificationCode);
}