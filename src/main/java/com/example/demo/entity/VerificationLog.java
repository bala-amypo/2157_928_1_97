package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification_logs")
public class VerificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Certificate must not be null")
    @ManyToOne
    private Certificate certificate;

    @NotNull(message = "Verification time is required")
    private LocalDateTime verifiedAt;

    @NotBlank(message = "Verification status is required")
    private String status;

    @NotBlank(message = "IP address is required")
    private String ipAddress;

    public VerificationLog() {
    }

    public VerificationLog(Long id, Certificate certificate,
                           LocalDateTime verifiedAt,
                           String status, String ipAddress) {
        this.id = id;
        this.certificate = certificate;
        this.verifiedAt = verifiedAt;
        this.status = status;
        this.ipAddress = ipAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
