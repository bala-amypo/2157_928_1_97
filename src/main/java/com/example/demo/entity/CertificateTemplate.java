package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "certificate_templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Template name is required")
    @Column(unique = true)
    private String templateName;

    @NotBlank(message = "Background URL is required")
    private String backgroundUrl;

    @NotBlank(message = "Font style is required")
    private String fontStyle;

    @NotBlank(message = "Signature name is required")
    private String signatureName;
}
