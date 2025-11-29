package com.recruiter.backend.domain.repository;

import com.recruiter.backend.infraestructure.entitys.JobEntity;
import java.util.List;
import java.util.Optional;

public interface JobRepository {
    JobEntity save(JobEntity entity);
    Optional<JobEntity> findById(Long id);
    List<JobEntity> findAll();
    List<JobEntity> findByCompanyId(Long companyId);
    List<JobEntity> findByAreaId(Long areaId);
    void deleteById(Long id);
    JobEntity update(JobEntity entity);
}
