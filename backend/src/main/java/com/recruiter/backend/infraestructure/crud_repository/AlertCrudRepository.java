package com.recruiter.backend.infraestructure.crud_repository;

import com.recruiter.backend.infraestructure.entitys.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertCrudRepository extends JpaRepository<AlertEntity, Long> {
    List<AlertEntity> findByUserId(Long userId);
}
