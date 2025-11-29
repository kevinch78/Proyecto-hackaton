package com.recruiter.backend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.recruiter.backend.infraestructure.entitys.CandidateEntity;

public interface CandidateRepository {

    CandidateEntity save(CandidateEntity entity);

    Optional<CandidateEntity> findById(Long id);

    Optional<CandidateEntity> findByEmail(String email);

    List<CandidateEntity> findAll();

    void deleteById(Long id);

    CandidateEntity update(CandidateEntity entity);
}
