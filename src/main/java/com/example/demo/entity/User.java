package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "users")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false)
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
}