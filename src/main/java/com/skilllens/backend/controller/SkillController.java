package com.skilllens.backend.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SkillController {

    @GetMapping("/skills/{role}")
    public Map<String, Object> getSkills(@PathVariable String role) {

        Map<String, Object> response = new HashMap<>();

        switch (role.toLowerCase()) {

            case "frontend":
                response.put("matchPercentage", 80);
                response.put(
                        "matchedSkills",
                        Arrays.asList("HTML", "CSS", "JavaScript", "React")
                );
                response.put(
                        "missingSkills",
                        Arrays.asList("TypeScript", "Next.js")
                );
                break;

            case "backend":
                response.put("matchPercentage", 65);
                response.put(
                        "matchedSkills",
                        Arrays.asList("Java", "Spring Boot", "MySQL")
                );
                response.put(
                        "missingSkills",
                        Arrays.asList("Microservices", "Docker")
                );
                break;

            case "devops":
                response.put("matchPercentage", 50);
                response.put(
                        "matchedSkills",
                        Arrays.asList("Linux", "Git")
                );
                response.put(
                        "missingSkills",
                        Arrays.asList("Kubernetes", "AWS", "Jenkins")
                );
                break;

            default:
                response.put("matchPercentage", 0);
                response.put("matchedSkills", Collections.emptyList());
                response.put("missingSkills", Collections.emptyList());
        }

        return response;
    }
}