package com.recruiter.backend.infraestructure.crud_repository;


import com.recruiter.backend.infraestructure.entitys.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CandidateCrudRepository extends JpaRepository<CandidateEntity, Long> {
    Optional<CandidateEntity> findByEmail(String email);
}

