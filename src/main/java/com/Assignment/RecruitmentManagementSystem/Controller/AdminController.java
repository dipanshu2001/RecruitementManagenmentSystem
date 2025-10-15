package com.Assignment.RecruitmentManagementSystem.Controller;
import com.Assignment.RecruitmentManagementSystem.DTO.ApplicantResponse;
import com.Assignment.RecruitmentManagementSystem.DTO.JobRequest;
import com.Assignment.RecruitmentManagementSystem.DTO.JobResponse;
import com.Assignment.RecruitmentManagementSystem.Service.JobService;
import com.Assignment.RecruitmentManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @PostMapping("/job")
    public ResponseEntity<JobResponse> createJob(@RequestBody JobRequest jobRequest, Principal principal) {
        return ResponseEntity.ok(jobService.createJob(jobRequest, principal.getName()));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<JobResponse> getJobDetails(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobService.getJobById(jobId));
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<ApplicantResponse>> getAllApplicants() {
        return ResponseEntity.ok(userService.getAllApplicants());
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<ApplicantResponse> getApplicant(@PathVariable Long applicantId) {
        return ResponseEntity.ok(userService.getApplicantById(applicantId));
    }
}
