package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "student_id")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "template_id")
    private CertificateTemplate template;
    
    @Column(unique = true)
    private String verificationCode;
    
    @Column(columnDefinition = "TEXT")
    private String qrCodeUrl;
    
    @Builder.Default
    private LocalDateTime issuedDate = LocalDateTime.now();
}