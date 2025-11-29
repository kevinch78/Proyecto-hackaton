package com.recruiter.backend.infraestructure.crud_repository;

import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompanyCrudRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByName(String name);
}
