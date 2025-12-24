package com.example.demo.repository;

import com.example.demo.entity.VerificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationLogRepository extends JpaRepository<VerificationLog, Long> {

    /**
     * Get all verification logs for a specific certificate.
     * Used in VerificationController to show verification history.
     *
     * @param certificateId the ID of the certificate
     * @return list of verification logs for the certificate
     */
    List<VerificationLog> findByCertificateId(Long certificateId);
}
