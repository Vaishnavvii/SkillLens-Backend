package com.skilllens.backend.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeService {

    public String extractText(MultipartFile file) throws IOException {

        // Load the uploaded PDF
        PDDocument document = Loader.loadPDF(file.getBytes());

        // Create PDF text extractor
        PDFTextStripper pdfTextStripper = new PDFTextStripper();

        // Extract all text from the PDF
        String resumeText = pdfTextStripper.getText(document);

        // Close the PDF document
        document.close();

        // Return extracted text
        return resumeText;
    }
}