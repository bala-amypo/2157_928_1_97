package com.example.demo.repository;

import com.example.demo.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    Optional<Certificate> findByVerificationCode(String verificationCode);

    // Needed for verify(String verificationCode, String email)
    Optional<Certificate> findByVerificationCodeAndStudentEmail(String verificationCode, String email);
}
