package com.recruiter.backend.domain.repository;

import com.recruiter.backend.infraestructure.entitys.AiAnalysisLogEntity;
import java.util.List;
import java.util.Optional;

public interface AiAnalysisLogRepository {
    AiAnalysisLogEntity save(AiAnalysisLogEntity entity);
    Optional<AiAnalysisLogEntity> findById(Long id);
    List<AiAnalysisLogEntity> findAll();
    List<AiAnalysisLogEntity> findByApplicationId(Long applicationId);
}
