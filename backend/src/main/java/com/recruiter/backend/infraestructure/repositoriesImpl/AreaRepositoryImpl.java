package com.recruiter.backend.infraestructure.repositoriesImpl;

import com.recruiter.backend.domain.repository.AreaRepository;
import com.recruiter.backend.infraestructure.crud_repository.AreaCrudRepository;
import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AreaRepositoryImpl implements AreaRepository {

    private final AreaCrudRepository crudRepository;

    @Override
    public AreaEntity save(AreaEntity entity) {
        return crudRepository.save(entity);
    }

    @Override
    public Optional<AreaEntity> findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<AreaEntity> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public List<AreaEntity> findByCompanyId(Long companyId) {
        return crudRepository.findByCompanyId(companyId);
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }

    @Override
    public AreaEntity update(AreaEntity entity) {
        return crudRepository.save(entity);
    }
}
