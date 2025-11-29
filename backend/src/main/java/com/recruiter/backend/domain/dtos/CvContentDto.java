package com.recruiter.backend.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CvContentDto {
    private String fullText;
    private Set<String> extractedSkills;
    private Set<String> extractedTechnologies;
    private Integer yearsOfExperience;
}
