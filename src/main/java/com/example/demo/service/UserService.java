package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.List;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
}

public interface StudentService {
    Student addStudent(Student s);
    List<Student> getAllStudents();
    Student findById(Long id);
}

public interface TemplateService {
    CertificateTemplate addTemplate(CertificateTemplate t);
    List<CertificateTemplate> getAll();
}

public interface CertificateService {
    Certificate generateCertificate(Long studentId, Long templateId);
    Certificate getCertificate(Long id);
    Certificate findByVerificationCode(String code);
    List<Certificate> findByStudentId(Long studentId);
}

public interface VerificationService {
    Certificate verify(String code, String ip);
}
