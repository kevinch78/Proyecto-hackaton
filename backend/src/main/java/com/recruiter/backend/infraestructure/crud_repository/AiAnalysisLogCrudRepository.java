package com.recruiter.backend.infraestructure.crud_repository;

import com.recruiter.backend.infraestructure.entitys.AiAnalysisLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AiAnalysisLogCrudRepository extends JpaRepository<AiAnalysisLogEntity, Long> {
    List<AiAnalysisLogEntity> findByApplicationId(Long applicationId);
}
