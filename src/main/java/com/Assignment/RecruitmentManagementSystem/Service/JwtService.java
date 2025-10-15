package com.Assignment.RecruitmentManagementSystem.Service;

public interface JwtService {
    String generateToken(String username);
    boolean validateToken(String token);
    String extractUsername(String token);
}
