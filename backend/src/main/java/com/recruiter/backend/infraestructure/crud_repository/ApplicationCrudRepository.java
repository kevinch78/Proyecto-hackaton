package com.recruiter.backend.infraestructure.crud_repository;

import com.recruiter.backend.infraestructure.entitys.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationCrudRepository extends JpaRepository<ApplicationEntity, Long> {
    List<ApplicationEntity> findByJobId(Long jobId);
    List<ApplicationEntity> findByCandidateId(Long candidateId);
}
