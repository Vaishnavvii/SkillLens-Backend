package com.skilllens.backend.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillAnalysisService {

    // List of skills that SkillLens will search for in the resume
    private static final String[] SKILLS = {
            "Java",
            "Spring Boot",
            "React",
            "HTML",
            "CSS",
            "JavaScript",
            "TypeScript",
            "MySQL",
            "MongoDB",
            "Git",
            "GitHub",
            "REST API",
            "REST APIs",
            "Hibernate",
            "JPA",
            "Spring Security",
            "JWT",
            "Maven",
            "Docker",
            "Kubernetes",
            "AWS",
            "Microservices",
            "Node.js",
            "Express.js",
            "Python",
            "C++",
            "Bootstrap",
            "Tailwind CSS",
            "Redux",
            "Postman"
    };

    public List<String> extractSkills(String resumeText) {

        List<String> extractedSkills = new ArrayList<>();

        if (resumeText == null || resumeText.isEmpty()) {
            return extractedSkills;
        }

        String lowerCaseResume = resumeText.toLowerCase();

        for (String skill : SKILLS) {

            if (lowerCaseResume.contains(skill.toLowerCase())) {

                extractedSkills.add(skill);
            }
        }

        return extractedSkills;
    }
}