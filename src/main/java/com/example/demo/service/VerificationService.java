package com.example.demo.service;

import com.example.demo.entity.Certificate;

public interface VerificationService {

    // Single parameter for verification code
    Certificate verify(String verificationCode);
}
