package com.Assignment.RecruitmentManagementSystem.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeParsed {
    private String education;
    private String experience;
    private String skills;
    private String name;
    private String email;
    private String phone;
}
