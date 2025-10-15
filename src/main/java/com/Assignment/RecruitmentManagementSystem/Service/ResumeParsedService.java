package com.Assignment.RecruitmentManagementSystem.Service;

import com.Assignment.RecruitmentManagementSystem.DTO.ResumeParsed;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeParsedService {
    ResumeParsed parseResume(MultipartFile file) throws Exception;
}
