package com.recruiter.backend.infraestructure.mapper;

import com.recruiter.backend.domain.dtos.ApplicationResponse;
import com.recruiter.backend.infraestructure.entitys.ApplicationEntity;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public ApplicationResponse toResponse(ApplicationEntity entity) {
        if (entity == null) return null;
        return new ApplicationResponse(
                entity.getId(),
                entity.getCandidate().getId(),
                entity.getJob().getId(),
                entity.getStatus(),
                entity.getMatchScore(),
                entity.getFinancialRisk(),
                entity.getAreaImpact(),
                entity.getAiAnalysis(),
                entity.getAiRecommendation(),
                entity.getNotes(),
                entity.getAppliedAt(),
                entity.getUpdatedAt()
        );
    }

    // No toEntity from a request, as it's created from other entities.
    // No update either, as updates are specific (e.g., status change).
}
