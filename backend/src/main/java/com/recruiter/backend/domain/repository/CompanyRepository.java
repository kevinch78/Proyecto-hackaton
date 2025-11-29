package com.recruiter.backend.domain.repository;

import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository {
    CompanyEntity save(CompanyEntity entity);
    Optional<CompanyEntity> findById(Long id);
    List<CompanyEntity> findAll();
    void deleteById(Long id);
    CompanyEntity update(CompanyEntity entity);
    Optional<CompanyEntity> findByName(String name);
}
