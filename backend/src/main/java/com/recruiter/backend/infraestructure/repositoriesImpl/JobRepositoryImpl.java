package com.recruiter.backend.infraestructure.repositoriesImpl;

import com.recruiter.backend.domain.repository.JobRepository;
import com.recruiter.backend.infraestructure.crud_repository.JobCrudRepository;
import com.recruiter.backend.infraestructure.entitys.JobEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JobRepositoryImpl implements JobRepository {

    private final JobCrudRepository crudRepository;

    @Override
    public JobEntity save(JobEntity entity) {
        return crudRepository.save(entity);
    }

    @Override
    public Optional<JobEntity> findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<JobEntity> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public List<JobEntity> findByCompanyId(Long companyId) {
        return crudRepository.findByCompanyId(companyId);
    }

    @Override
    public List<JobEntity> findByAreaId(Long areaId) {
        return crudRepository.findByAreaId(areaId);
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }

    @Override
    public JobEntity update(JobEntity entity) {
        return crudRepository.save(entity);
    }
}
