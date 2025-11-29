package com.recruiter.backend.infraestructure.repositoriesImpl;

import com.recruiter.backend.domain.repository.AlertRepository;
import com.recruiter.backend.infraestructure.crud_repository.AlertCrudRepository;
import com.recruiter.backend.infraestructure.entitys.AlertEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlertRepositoryImpl implements AlertRepository {

    private final AlertCrudRepository crudRepository;

    @Override
    public AlertEntity save(AlertEntity entity) {
        return crudRepository.save(entity);
    }

    @Override
    public Optional<AlertEntity> findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<AlertEntity> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public List<AlertEntity> findByUserId(Long userId) {
        return crudRepository.findByUserId(userId);
    }

    @Override
    public AlertEntity update(AlertEntity entity) {
        return crudRepository.save(entity);
    }
}
