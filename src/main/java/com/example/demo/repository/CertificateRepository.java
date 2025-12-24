package com.example.demo.repository;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    /**
     * Find a certificate by its unique verification code.
     * Used in verification flow.
     *
     * @param code the verification code starting with "VC-"
     * @return Optional containing the Certificate if found
     */
    Optional<Certificate> findByVerificationCode(String code);

    /**
     * Find all certificates issued to a particular student.
     *
     * @param student the Student entity
     * @return List of Certificate entities
     */
    List<Certificate> findByStudent(Student student);
}
