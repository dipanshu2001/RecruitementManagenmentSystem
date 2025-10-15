package com.Assignment.RecruitmentManagementSystem.Entity;
import com.Assignment.RecruitmentManagementSystem.Enum.UserType;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    private String address;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String profileHeadline;

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL)
    private Profile profile;
}
