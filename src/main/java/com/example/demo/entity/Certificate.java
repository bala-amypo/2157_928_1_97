package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "certificates")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private CertificateTemplate template;

    private LocalDate issuedDate;

    @Column(length = 5000) // QR codes as Base64 strings can be long
    private String qrCodeUrl;

    @Column(unique = true, nullable = false)
    private String verificationCode;

    @OneToMany(mappedBy = "certificate", cascade = CascadeType.ALL)
    private List<VerificationLog> verificationLogs;
}