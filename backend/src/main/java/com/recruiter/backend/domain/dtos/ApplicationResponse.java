package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.ApplicationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {
    private Long id;
    private Long candidateId;
    private Long jobId;
    private ApplicationEntity.ApplicationStatus status;
    private Double matchScore;
    private Double financialRisk;
    private Double areaImpact;
    private String aiAnalysis;
    private String aiRecommendation;
    private String notes;
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;
}
