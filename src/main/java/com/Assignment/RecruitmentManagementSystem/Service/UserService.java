package com.Assignment.RecruitmentManagementSystem.Service;

import com.Assignment.RecruitmentManagementSystem.DTO.ApplicantResponse;
import com.Assignment.RecruitmentManagementSystem.DTO.SignupRequest;
import com.Assignment.RecruitmentManagementSystem.Entity.User;

import java.util.List;

public interface UserService {
    User registerUser(SignupRequest request);
    User getUserByEmail(String email);
    List<ApplicantResponse> getAllApplicants();
    ApplicantResponse getApplicantById(Long applicantId);
}
