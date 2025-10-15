package com.Assignment.RecruitmentManagementSystem.Service;

import com.Assignment.RecruitmentManagementSystem.Entity.Profile;
import com.Assignment.RecruitmentManagementSystem.Entity.User;
import com.Assignment.RecruitmentManagementSystem.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Profile getProfileForUser(User user) {
        return user.getProfile();
    }

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }
}
