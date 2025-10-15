package com.Assignment.RecruitmentManagementSystem.Service;

import com.Assignment.RecruitmentManagementSystem.DTO.ResumeParsed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ResumeParsedServiceImpl implements ResumeParsedService{
    private static final String API_URL = "https://api.apilayer.com/resume_parser/upload";
    private static final String API_KEY = "0bWeisRWoLj3UdXt3MXMSMWptYFIpQfS";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ResumeParsedServiceImpl() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public ResumeParsed parseResume(MultipartFile file) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("apikey", API_KEY);

        HttpEntity<byte[]> entity = new HttpEntity<>(file.getBytes(), headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IOException("Failed to parse resume: " + response.getStatusCode());
        }

        String responseBody = response.getBody();
        JsonNode rootNode = objectMapper.readTree(responseBody);


        String education = "";
        if (rootNode.has("education")) {
            education = StreamSupport.stream(rootNode.get("education").spliterator(), false)
                    .map(node -> node.path("name").asText())
                    .collect(Collectors.joining(", "));
        }


        String experience = "";
        if (rootNode.has("experience")) {
            experience = StreamSupport.stream(rootNode.get("experience").spliterator(), false)
                    .map(node -> node.path("name").asText())
                    .collect(Collectors.joining(", "));
        }


        String skills = "";
        if (rootNode.has("skills")) {
            skills = StreamSupport.stream(rootNode.get("skills").spliterator(), false)
                    .map(JsonNode::asText)
                    .collect(Collectors.joining(", "));
        }

        String name = rootNode.path("name").asText("");
        String email = rootNode.path("email").asText("");
        String phone = rootNode.path("phone").asText("");

        ResumeParsed parsed = new ResumeParsed();
        parsed.setEducation(education);
        parsed.setExperience(experience);
        parsed.setSkills(skills);
        parsed.setName(name);
        parsed.setEmail(email);
        parsed.setPhone(phone);

        return parsed;
    }
}
