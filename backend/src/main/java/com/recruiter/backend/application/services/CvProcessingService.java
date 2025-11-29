package com.recruiter.backend.application.services;

import com.recruiter.backend.domain.dtos.CvContentDto;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CvProcessingService {

    // Simple keyword lists for demonstration
    private static final Set<String> PROGRAMMING_SKILLS = new HashSet<>(Arrays.asList(
            "java", "python", "javascript", "c#", "c++", "ruby", "go", "swift", "kotlin", "php"
    ));
    private static final Set<String> TECHNOLOGIES = new HashSet<>(Arrays.asList(
            "spring boot", "react", "angular", "vue.js", "docker", "kubernetes", "aws", "azure", "gcp",
            "sql", "nosql", "mongodb", "postgresql", "kafka", "microservices", "rest", "graphql"
    ));

    public CvContentDto processCv(Path cvFilePath) throws Exception {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(-1); // -1 for unlimited
        Metadata metadata = new Metadata();

        try (InputStream stream = Files.newInputStream(cvFilePath)) {
            parser.parse(stream, handler, metadata);
            String content = handler.toString();
            
            // Extract information
            Set<String> extractedSkills = extractKeywords(content, PROGRAMMING_SKILLS);
            Set<String> extractedTechnologies = extractKeywords(content, TECHNOLOGIES);
            Integer yearsOfExperience = extractYearsOfExperience(content);

            return new CvContentDto(content, extractedSkills, extractedTechnologies, yearsOfExperience);

        } catch (Exception e) {
            throw new Exception("Error processing CV file: " + e.getMessage(), e);
        }
    }

    private Set<String> extractKeywords(String text, Set<String> keywordList) {
        Set<String> foundKeywords = new HashSet<>();
        String lowerCaseText = text.toLowerCase();
        for (String keyword : keywordList) {
            if (lowerCaseText.contains(keyword)) {
                foundKeywords.add(keyword);
            }
        }
        return foundKeywords;
    }

    private Integer extractYearsOfExperience(String text) {
        // Simple regex to find "X years of experience" or similar patterns
        Pattern pattern = Pattern.compile("(\\d+)\\s+(?:years?|yrs?)\\s+of\\s+experience", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0; // Default to 0 if not found
    }
}
