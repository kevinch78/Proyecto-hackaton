package com.recruiter.backend.infraestructure.repositoriesImpl;

import com.recruiter.backend.domain.repository.ApplicationRepository;
import com.recruiter.backend.infraestructure.crud_repository.ApplicationCrudRepository;
import com.recruiter.backend.infraestructure.entitys.ApplicationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final ApplicationCrudRepository crudRepository;

    @Override
    public ApplicationEntity save(ApplicationEntity entity) {
        return crudRepository.save(entity);
    }

    @Override
    public Optional<ApplicationEntity> findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<ApplicationEntity> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public List<ApplicationEntity> findByJobId(Long jobId) {
        return crudRepository.findByJobId(jobId);
    }

    @Override
    public List<ApplicationEntity> findByCandidateId(Long candidateId) {
        return crudRepository.findByCandidateId(candidateId);
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }

    @Override
    public ApplicationEntity update(ApplicationEntity entity) {
        return crudRepository.save(entity);
    }
}
