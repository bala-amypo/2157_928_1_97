package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
