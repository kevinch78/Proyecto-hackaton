package com.recruiter.backend.domain.repository;

import com.recruiter.backend.infraestructure.entitys.AlertEntity;
import java.util.List;
import java.util.Optional;

public interface AlertRepository {
    AlertEntity save(AlertEntity entity);
    Optional<AlertEntity> findById(Long id);
    List<AlertEntity> findAll();
    List<AlertEntity> findByUserId(Long userId);
    AlertEntity update(AlertEntity entity);
}
