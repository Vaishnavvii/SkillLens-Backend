package com.skilllens.backend.controller;

import com.skilllens.backend.service.ResumeService;
import com.skilllens.backend.service.SkillAnalysisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume(
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("No file selected");
        }

        try {

            // Extract text from PDF
            String text = resumeService.extractText(file);

            // Extract skills from text
            List<String> skills = skillAnalysisService.extractSkills(text);

            // Print extracted skills
            System.out.println("========== EXTRACTED SKILLS ==========");
            skills.forEach(System.out::println);
            System.out.println("======================================");

            // Prepare JSON response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Resume uploaded successfully!");
            response.put("skills", skills);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError()
                    .body("Failed to process resume.");
        }
    }
}