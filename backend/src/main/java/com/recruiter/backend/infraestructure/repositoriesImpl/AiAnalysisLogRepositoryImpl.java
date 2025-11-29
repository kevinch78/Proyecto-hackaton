package com.recruiter.backend.infraestructure.repositoriesImpl;

import com.recruiter.backend.domain.repository.AiAnalysisLogRepository;
import com.recruiter.backend.infraestructure.crud_repository.AiAnalysisLogCrudRepository;
import com.recruiter.backend.infraestructure.entitys.AiAnalysisLogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AiAnalysisLogRepositoryImpl implements AiAnalysisLogRepository {

    private final AiAnalysisLogCrudRepository crudRepository;

    @Override
    public AiAnalysisLogEntity save(AiAnalysisLogEntity entity) {
        return crudRepository.save(entity);
    }

    @Override
    public Optional<AiAnalysisLogEntity> findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<AiAnalysisLogEntity> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public List<AiAnalysisLogEntity> findByApplicationId(Long applicationId) {
        return crudRepository.findByApplicationId(applicationId);
    }
}
