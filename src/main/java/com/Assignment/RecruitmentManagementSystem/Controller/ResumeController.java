package com.Assignment.RecruitmentManagementSystem.Controller;
import com.Assignment.RecruitmentManagementSystem.DTO.ResumeParsed;
import com.Assignment.RecruitmentManagementSystem.Entity.Profile;
import com.Assignment.RecruitmentManagementSystem.Entity.User;
import com.Assignment.RecruitmentManagementSystem.Service.ProfileService;
import com.Assignment.RecruitmentManagementSystem.Service.ResumeParsedService;
import com.Assignment.RecruitmentManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping
public class ResumeController {

    @Autowired
    private ResumeParsedService resumeParsedService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @PostMapping("/uploadResume")
    @PreAuthorize("hasAuthority('APPLICANT')")
    public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file, Principal principal) throws Exception {
        User user = userService.getUserByEmail(principal.getName());

        ResumeParsed parsed = resumeParsedService.parseResume(file);

        Profile profile = user.getProfile() != null ? user.getProfile() : new Profile();
        profile.setApplicant(user);
        // You might want to save the file as well and set the path here, skipped for brevity
        profile.setSkills(parsed.getSkills());
        profile.setEducation(parsed.getEducation());
        profile.setExperience(parsed.getExperience());
        profile.setName(parsed.getName());
        profile.setEmail(parsed.getEmail());
        profile.setPhone(parsed.getPhone());

        profileService.save(profile);

        return ResponseEntity.ok("Resume uploaded and processed successfully");
    }
}
