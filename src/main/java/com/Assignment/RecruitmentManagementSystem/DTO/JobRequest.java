package com.Assignment.RecruitmentManagementSystem.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    private String title;
    private String description;
    private String companyName;
}
