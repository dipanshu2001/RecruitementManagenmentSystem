package com.Assignment.RecruitmentManagementSystem.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantResponse {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String profileHeadline;
}
