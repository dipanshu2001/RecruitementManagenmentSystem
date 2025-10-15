package com.Assignment.RecruitmentManagementSystem.Service;

import com.Assignment.RecruitmentManagementSystem.DTO.JobRequest;
import com.Assignment.RecruitmentManagementSystem.DTO.JobResponse;
import com.Assignment.RecruitmentManagementSystem.Entity.Job;

import java.util.List;

public interface JobService {
    JobResponse createJob(JobRequest request, String adminEmail);
    List<JobResponse> getAllJobs();
    JobResponse getJobById(Long jobId);
    void applyToJob(Long jobId, String applicantEmail);
}
