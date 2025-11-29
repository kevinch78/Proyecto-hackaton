package com.recruiter.backend.infraestructure.crud_repository;

import com.recruiter.backend.infraestructure.entitys.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobCrudRepository extends JpaRepository<JobEntity, Long> {
    List<JobEntity> findByCompanyId(Long companyId);
    List<JobEntity> findByAreaId(Long areaId);
}
