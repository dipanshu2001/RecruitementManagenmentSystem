package com.Assignment.RecruitmentManagementSystem.Service;

import com.Assignment.RecruitmentManagementSystem.Entity.Job;
import com.Assignment.RecruitmentManagementSystem.Entity.User;
import com.Assignment.RecruitmentManagementSystem.Repository.JobRepository;
import com.Assignment.RecruitmentManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void applyToJob(Long jobId, String applicantEmail) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        Optional<User> userOptional = userRepository.findByEmail(applicantEmail);

        if (!jobOptional.isPresent() || !userOptional.isPresent()) {
            throw new RuntimeException("Job or Applicant not found");
        }

        Job job = jobOptional.get();
        User applicant = userOptional.get();

        if (!job.getApplicants().contains(applicant)) {
            job.getApplicants().add(applicant);
            job.setTotalApplications(job.getApplicants().size());
            jobRepository.save(job);
        }
    }
}
