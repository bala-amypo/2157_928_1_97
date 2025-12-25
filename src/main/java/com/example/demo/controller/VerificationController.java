package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/{verificationCode}")
    public VerificationLog verify(@PathVariable String verificationCode, HttpServletRequest request){
        String clientIp = request.getRemoteAddr();
        return verificationService.verifyCertificate(verificationCode,clientIp);
    }

    @GetMapping("/logs/{certificateId}")
    public List<VerificationLog> getLogs(@PathVariable Long certificateId){
        return verificationService.getLogsByCertificate(certificateId);
    }
}
