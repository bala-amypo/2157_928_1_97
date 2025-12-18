package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/{code}")
    public VerificationLog verify(@PathVariable String code,
                                  @RequestHeader("X-Client-IP") String ip) {
        return verificationService.verifyCertificate(code, ip);
    }

    @GetMapping("/logs/{certificateId}")
    public List<VerificationLog> logs(@PathVariable Long certificateId) {
        return verificationService.getLogsByCertificate(certificateId);
    }
}
