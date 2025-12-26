package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VerificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Certificate certificate;

    private LocalDateTime verifiedAt;
    private String status;
    private String ipAddress;

    public VerificationLog() {}

    public void setCertificate(Certificate certificate) { this.certificate = certificate; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }
    public void setStatus(String status) { this.status = status; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}
