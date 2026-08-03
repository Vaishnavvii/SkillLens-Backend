package com.skilllens.backend.controller;

import com.skilllens.backend.service.ResumeService;
import com.skilllens.backend.service.SkillAnalysisService;
import com.skilllens.backend.service.SkillGapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "http://localhost:3000")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private SkillAnalysisService skillAnalysisService;

    @Autowired
    private SkillGapService skillGapService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("role") String role) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected");
        }

        try {

            // Extract resume text
            String resumeText = resumeService.extractText(file);

            // Extract skills
            List<String> extractedSkills =
                    skillAnalysisService.extractSkills(resumeText);

            System.out.println("========== EXTRACTED SKILLS ==========");
            extractedSkills.forEach(System.out::println);
            System.out.println("======================================");

            // Analyze skills
            Map<String, Object> result =
                    skillGapService.analyzeSkills(
                            extractedSkills,
                            role
                    );

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError()
                    .body("Failed to process resume.");
        }
    }
}