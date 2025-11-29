package com.recruiter.backend.infraestructure.mapper;

import com.recruiter.backend.domain.dtos.CandidateResponse;
import com.recruiter.backend.domain.dtos.CreateCandidateRequest;
import com.recruiter.backend.domain.dtos.UpdateCandidateRequest;
import com.recruiter.backend.infraestructure.entitys.CandidateEntity;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    public CandidateResponse toResponse(CandidateEntity entity) {
        if (entity == null) return null;
        return new CandidateResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getScoreIa(),
                entity.getRequestedSalary(),
                entity.getSkills(),
                entity.getTechnologies(),
                entity.getYearsOfExperience(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public CandidateEntity toEntity(CreateCandidateRequest request) {
        if (request == null) return null;
        CandidateEntity entity = new CandidateEntity();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setRequestedSalary(request.getRequestedSalary());
        entity.setYearsOfExperience(request.getYearsOfExperience());
        entity.setSkills(request.getSkills());
        entity.setTechnologies(request.getTechnologies());
        // Set default values for non-nullable fields
        entity.setStatus(CandidateEntity.CandidateStatus.ACTIVE);
        entity.setScoreIa(0.0);
        return entity;
    }

    public void updateEntityFromRequest(UpdateCandidateRequest request, CandidateEntity entity) {
        if (request == null || entity == null) return;

        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            entity.setPhone(request.getPhone());
        }
        if (request.getRequestedSalary() != null) {
            entity.setRequestedSalary(request.getRequestedSalary());
        }
        if (request.getYearsOfExperience() != null) {
            entity.setYearsOfExperience(request.getYearsOfExperience());
        }
        if (request.getSkills() != null) {
            entity.setSkills(request.getSkills());
        }
        if (request.getTechnologies() != null) {
            entity.setTechnologies(request.getTechnologies());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
    }
}
