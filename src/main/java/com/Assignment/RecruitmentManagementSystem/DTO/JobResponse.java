package com.Assignment.RecruitmentManagementSystem.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String companyName;
    private LocalDateTime postedOn;
    private int totalApplications;
    private List<ApplicantResponse> applicants;
}
