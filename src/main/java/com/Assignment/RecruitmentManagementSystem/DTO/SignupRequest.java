package com.Assignment.RecruitmentManagementSystem.DTO;

import com.Assignment.RecruitmentManagementSystem.Enum.UserType;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private String profileHeadline;
    private String address;
}
