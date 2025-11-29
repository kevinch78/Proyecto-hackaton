package com.recruiter.backend.infraestructure.repositoriesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.recruiter.backend.domain.repository.CandidateRepository;
import com.recruiter.backend.infraestructure.crud_repository.CandidateCrudRepository;
import com.recruiter.backend.infraestructure.entitys.CandidateEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CandidateRepositoryImpl implements CandidateRepository {

    private final CandidateCrudRepository crudRepository;

    @Override
    public CandidateEntity save(CandidateEntity entity) {
        return crudRepository.save(entity);
    }

    @Override
    public Optional<CandidateEntity> findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<CandidateEntity> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public Optional<CandidateEntity> findByEmail(String email) {
        return crudRepository.findByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }

    @Override
    public CandidateEntity update(CandidateEntity entity) {
        // The save method handles both creation and updates.
        return crudRepository.save(entity);
    }
}