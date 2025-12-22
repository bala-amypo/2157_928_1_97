package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "certificate_templates")
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

    public CertificateTemplate() {
    }

    public CertificateTemplate(Long id, String templateName, String backgroundUrl,
                               String fontStyle, String signatureName) {
        this.id = id;
        this.templateName = templateName;
        this.backgroundUrl = backgroundUrl;
        this.fontStyle = fontStyle;
        this.signatureName = signatureName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getSignatureName() {
        return signatureName;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }
}
