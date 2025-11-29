package com.recruiter.backend.domain.repository;

import com.recruiter.backend.infraestructure.entitys.ApplicationEntity;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository {
    ApplicationEntity save(ApplicationEntity entity);
    Optional<ApplicationEntity> findById(Long id);
    List<ApplicationEntity> findAll();
    List<ApplicationEntity> findByJobId(Long jobId);
    List<ApplicationEntity> findByCandidateId(Long candidateId);
    void deleteById(Long id);
    ApplicationEntity update(ApplicationEntity entity);
}
