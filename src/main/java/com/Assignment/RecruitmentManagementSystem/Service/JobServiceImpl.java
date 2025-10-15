package com.Assignment.RecruitmentManagementSystem.Service;

import com.Assignment.RecruitmentManagementSystem.DTO.ApplicantResponse;
import com.Assignment.RecruitmentManagementSystem.DTO.JobRequest;
import com.Assignment.RecruitmentManagementSystem.DTO.JobResponse;
import com.Assignment.RecruitmentManagementSystem.Entity.Job;
import com.Assignment.RecruitmentManagementSystem.Entity.User;
import com.Assignment.RecruitmentManagementSystem.Repository.JobRepository;
import com.Assignment.RecruitmentManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JobResponse createJob(JobRequest request, String adminEmail) {
        User admin = userRepository.findByEmail(adminEmail)
                .orElseThrow(() -> new RuntimeException("Admin not found with email: " + adminEmail));

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCompanyName(request.getCompanyName());
        job.setPostedOn(LocalDateTime.now());
        job.setTotalApplications(0);
        job.setPostedBy(admin);

        Job savedJob = jobRepository.save(job);

        // Map savedJob entity to JobResponse DTO
        return mapJobToJobResponse(savedJob, false);
    }

    private JobResponse mapJobToJobResponse(Job job, boolean includeApplicants) {
        List<ApplicantResponse> applicants = Collections.emptyList();
        if (includeApplicants && job.getApplicants() != null) {
            applicants = job.getApplicants().stream()
                    .map(user -> new ApplicantResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getAddress(),
                            user.getProfileHeadline()))
                    .collect(Collectors.toList());
        }

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getCompanyName(),
                job.getPostedOn(),
                job.getTotalApplications(),
                applicants);
    }

    @Override
    public List<JobResponse> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(job ->
                new JobResponse(
                        job.getId(),
                        job.getTitle(),
                        job.getDescription(),
                        job.getCompanyName(),
                        job.getPostedOn(),
                        job.getTotalApplications(),
                        Collections.emptyList() // No applicants in this endpoint
                )
        ).collect(Collectors.toList());
    }

    @Override
    public JobResponse getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));

        List<ApplicantResponse> applicants = job.getApplicants() == null ?
                Collections.emptyList() :
                job.getApplicants().stream()
                        .map(user -> new ApplicantResponse(user.getId(), user.getName(), user.getEmail(), user.getAddress(), user.getProfileHeadline()))
                        .collect(Collectors.toList());

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getCompanyName(),
                job.getPostedOn(),
                job.getTotalApplications(),
                applicants
        );
    }

    @Override
    public void applyToJob(Long jobId, String applicantEmail) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));

        User applicant = userRepository.findByEmail(applicantEmail)
                .orElseThrow(() -> new RuntimeException("Applicant not found with email: " + applicantEmail));

        // You’ll add your job-application relationship here (if you have a join table)
        job.setTotalApplications(job.getTotalApplications() + 1);

        jobRepository.save(job);
    }
}

