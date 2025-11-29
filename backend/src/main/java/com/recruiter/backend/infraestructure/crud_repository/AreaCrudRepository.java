package com.recruiter.backend.infraestructure.crud_repository;

import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AreaCrudRepository extends JpaRepository<AreaEntity, Long> {
    List<AreaEntity> findByCompanyId(Long companyId);
}
