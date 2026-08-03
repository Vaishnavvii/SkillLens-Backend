package com.skilllens.backend.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillGapService {

    private static final Map<String, List<String>> ROLE_SKILLS = new HashMap<>();

    static {

        ROLE_SKILLS.put("frontend", Arrays.asList(
                "HTML",
                "CSS",
                "JavaScript",
                "React",
                "TypeScript",
                "Git"
        ));

        ROLE_SKILLS.put("backend", Arrays.asList(
                "Java",
                "Spring Boot",
                "MySQL",
                "REST API",
                "Git",
                "Docker",
                "Microservices"
        ));

        ROLE_SKILLS.put("devops", Arrays.asList(
                "Linux",
                "Git",
                "Docker",
                "Kubernetes",
                "AWS",
                "Jenkins"
        ));
    }

    public Map<String, Object> analyzeSkills(
            List<String> extractedSkills,
            String role) {

        List<String> requiredSkills =
                ROLE_SKILLS.getOrDefault(
                        role.toLowerCase(),
                        Collections.emptyList());

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : requiredSkills) {

            if (extractedSkills.contains(skill)) {

                matchedSkills.add(skill);

            } else {

                missingSkills.add(skill);
            }
        }

        int matchPercentage = 0;

        if (!requiredSkills.isEmpty()) {

            matchPercentage =
                    (matchedSkills.size() * 100) /
                            requiredSkills.size();
        }

        Map<String, Object> response = new HashMap<>();

        response.put("matchPercentage", matchPercentage);
        response.put("matchedSkills", matchedSkills);
        response.put("missingSkills", missingSkills);

        return response;
    }
}