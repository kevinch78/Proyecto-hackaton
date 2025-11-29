package com.recruiter.backend.domain.repository;

import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import java.util.List;
import java.util.Optional;

public interface AreaRepository {
    AreaEntity save(AreaEntity entity);
    Optional<AreaEntity> findById(Long id);
    List<AreaEntity> findAll();
    List<AreaEntity> findByCompanyId(Long companyId);
    void deleteById(Long id);
    AreaEntity update(AreaEntity entity);
}
