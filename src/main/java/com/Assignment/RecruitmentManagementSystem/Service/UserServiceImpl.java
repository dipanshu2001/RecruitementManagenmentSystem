package com.Assignment.RecruitmentManagementSystem.Service;

import com.Assignment.RecruitmentManagementSystem.DTO.ApplicantResponse;
import com.Assignment.RecruitmentManagementSystem.DTO.SignupRequest;
import com.Assignment.RecruitmentManagementSystem.Entity.User;
import com.Assignment.RecruitmentManagementSystem.Enum.UserType;
import com.Assignment.RecruitmentManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(SignupRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUserType(request.getUserType());
        user.setProfileHeadline(request.getProfileHeadline());
        user.setAddress(request.getAddress());

        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
         return userRepository.findByEmail(email)
                 .orElseThrow(()->new RuntimeException("User not found"));
    }
    @Override
    public List<ApplicantResponse> getAllApplicants() {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserType() == UserType.APPLICANT)
                .map(user -> new ApplicantResponse(user.getId(), user.getName(), user.getEmail(),
                        user.getAddress(), user.getProfileHeadline()))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicantResponse getApplicantById(Long applicantId) {
        User user = userRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        return new ApplicantResponse(user.getId(), user.getName(), user.getEmail(),
                user.getAddress(), user.getProfileHeadline());
    }
}
