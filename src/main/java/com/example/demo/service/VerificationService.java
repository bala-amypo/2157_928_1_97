package com.example.demo.service;

import com.example.demo.entity.VerificationLog;
import java.util.List;

public interface VerificationService {

    /**
     * Verifies a certificate by its verification code.
     * Records the attempt along with the client IP.
     *
     * @param verificationCode Unique verification code of the certificate
     * @param clientIp IP address of the client performing the verification
     * @return Created VerificationLog entry
     */
    VerificationLog verifyCertificate(String verificationCode, String clientIp);

    /**
     * Retrieves all verification logs for a given certificate.
     *
     * @param certificateId ID of the certificate
     * @return List of VerificationLog entries
     */
    List<VerificationLog> getLogsByCertificate(Long certificateId);
}
