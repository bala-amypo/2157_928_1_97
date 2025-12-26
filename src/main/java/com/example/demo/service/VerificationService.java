package com.example.demo.service;

import com.example.demo.entity.Certificate;

public interface VerificationService {

    // Updated to accept two parameters
    Certificate verify(String verificationCode, String email);
}
