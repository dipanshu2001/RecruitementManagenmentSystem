package com.Assignment.RecruitmentManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User applicant;
    private String resumeFileAddress;
    @Column(length=10000,columnDefinition = "TEXT")
    private String skills;
    @Column(length=10000,columnDefinition = "TEXT")
    private String education;
    @Column(length=10000,columnDefinition = "TEXT")
    private String experience;
    private String phone;
    private String name;
    private String email;
}

