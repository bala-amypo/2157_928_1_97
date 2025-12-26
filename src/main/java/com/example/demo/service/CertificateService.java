package com.example.demo.service;

import com.example.demo.entity.Certificate;
import java.util.List;

public interface CertificateService {

    Certificate save(Certificate certificate);

    List<Certificate> findAll();

    Certificate findById(Long id);
}
