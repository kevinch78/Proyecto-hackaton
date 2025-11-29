package com.recruiter.backend.infraestructure.crud_repository;

import com.recruiter.backend.infraestructure.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserCrudRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
