package com.example.demo.service;

import com.example.demo.entity.Certificate;

public interface VerificationService {

    // Accept both verificationCode and email
    Certificate verify(String verificationCode, String email);
}
