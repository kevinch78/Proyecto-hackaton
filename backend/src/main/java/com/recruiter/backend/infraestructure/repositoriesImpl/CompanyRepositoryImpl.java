package com.recruiter.backend.infraestructure.repositoriesImpl;

import com.recruiter.backend.domain.repository.CompanyRepository;
import com.recruiter.backend.infraestructure.crud_repository.CompanyCrudRepository;
import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    private final CompanyCrudRepository crudRepository;

    @Override
    public CompanyEntity save(CompanyEntity entity) {
        return crudRepository.save(entity);
    }

    @Override
    public Optional<CompanyEntity> findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<CompanyEntity> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }

    @Override
    public CompanyEntity update(CompanyEntity entity) {
        return crudRepository.save(entity);
    }

    @Override
    public Optional<CompanyEntity> findByName(String name) {
        return crudRepository.findByName(name);
    }
}
