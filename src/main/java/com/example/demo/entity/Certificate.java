package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Student must not be null")
    @ManyToOne
    private Student student;

    @NotNull(message = "Certificate template must not be null")
    @ManyToOne
    private CertificateTemplate template;

    @NotNull(message = "Issued date is required")
    private LocalDate issuedDate;

    @NotBlank(message = "QR Code URL is required")
    private String qrCodeUrl;

    @NotBlank(message = "Verification code is required")
    @Column(unique = true)
    private String verificationCode;

    public Certificate() {
    }

    public Certificate(Long id, Student student, CertificateTemplate template,
                       LocalDate issuedDate, String qrCodeUrl, String verificationCode) {
        this.id = id;
        this.student = student;
        this.template = template;
        this.issuedDate = issuedDate;
        this.qrCodeUrl = qrCodeUrl;
        this.verificationCode = verificationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CertificateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(CertificateTemplate template) {
        this.template = template;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
