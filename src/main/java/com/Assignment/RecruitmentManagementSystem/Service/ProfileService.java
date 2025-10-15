package com.Assignment.RecruitmentManagementSystem.Service;

import com.Assignment.RecruitmentManagementSystem.Entity.Profile;
import com.Assignment.RecruitmentManagementSystem.Entity.User;

public interface ProfileService {
    Profile getProfileForUser(User user);
    Profile save(Profile profile);
}
