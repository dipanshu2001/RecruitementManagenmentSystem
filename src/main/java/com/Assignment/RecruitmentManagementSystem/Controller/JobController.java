package com.Assignment.RecruitmentManagementSystem.Controller;
import com.Assignment.RecruitmentManagementSystem.DTO.JobResponse;
import com.Assignment.RecruitmentManagementSystem.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/apply")
    @PreAuthorize("hasAuthority('APPLICANT')")
    public ResponseEntity<?> applyForJob(@RequestParam Long job_id, Principal principal) {
        jobService.applyToJob(job_id, principal.getName());
        return ResponseEntity.ok("Applied to job successfully");
    }
}