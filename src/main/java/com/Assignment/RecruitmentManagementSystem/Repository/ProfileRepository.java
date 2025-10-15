package com.Assignment.RecruitmentManagementSystem.Repository;

import com.Assignment.RecruitmentManagementSystem.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
