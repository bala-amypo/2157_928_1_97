package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "certificates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation: Many certificates belong to one student
    @ManyToOne
    @NotNull(message = "Student must not be null")
    private Student student;

    // Relation: Many certificates belong to one template
    @ManyToOne
    @NotNull(message = "Certificate template must not be null")
    private CertificateTemplate template;

    // Issue date of the certificate
    @NotNull(message = "Issued date is required")
    private LocalDate issuedDate;

    // QR code in Base64 format (data:image/png;base64,...)
    @NotBlank(message = "QR Code URL is required")
    private String qrCodeUrl;

    // Unique verification code starting with "VC-"
    @NotBlank(message = "Verification code is required")
    @Column(unique = true)
    private String verificationCode;
}
