package com.Assignment.RecruitmentManagementSystem.Repository;

import com.Assignment.RecruitmentManagementSystem.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
