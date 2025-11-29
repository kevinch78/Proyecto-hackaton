package com.recruiter.backend.infraestructure.mapper;

import com.recruiter.backend.domain.dtos.JobResponse;
import com.recruiter.backend.domain.dtos.CreateJobRequest;
import com.recruiter.backend.domain.dtos.UpdateJobRequest;
import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import com.recruiter.backend.infraestructure.entitys.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobResponse toResponse(JobEntity entity) {
        if (entity == null) return null;
        return new JobResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getSalaryMin(),
                entity.getSalaryMax(),
                entity.getRequiredSkills(),
                entity.getRequiredTechnologies(),
                entity.getYearsExperienceRequired(),
                entity.getLevel(),
                entity.getStatus(),
                entity.getType(),
                entity.getOpenings(),
                entity.getCompany().getId(),
                entity.getArea().getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public JobEntity toEntity(CreateJobRequest request, CompanyEntity company, AreaEntity area) {
        if (request == null) return null;
        JobEntity entity = new JobEntity();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setSalaryMin(request.getSalaryMin());
        entity.setSalaryMax(request.getSalaryMax());
        entity.setRequiredSkills(request.getRequiredSkills());
        entity.setRequiredTechnologies(request.getRequiredTechnologies());
        entity.setYearsExperienceRequired(request.getYearsExperienceRequired());
        entity.setLevel(request.getLevel());
        entity.setType(request.getType());
        entity.setOpenings(request.getOpenings());
        entity.setCompany(company);
        entity.setArea(area);
        // Set default values
        entity.setStatus(JobEntity.JobStatus.OPEN);
        return entity;
    }

    public void updateEntityFromRequest(UpdateJobRequest request, JobEntity entity) {
        if (request == null || entity == null) return;

        if (request.getTitle() != null) {
            entity.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
        if (request.getSalaryMin() != null) {
            entity.setSalaryMin(request.getSalaryMin());
        }
        if (request.getSalaryMax() != null) {
            entity.setSalaryMax(request.getSalaryMax());
        }
        if (request.getRequiredSkills() != null) {
            entity.setRequiredSkills(request.getRequiredSkills());
        }
        if (request.getRequiredTechnologies() != null) {
            entity.setRequiredTechnologies(request.getRequiredTechnologies());
        }
        if (request.getYearsExperienceRequired() != null) {
            entity.setYearsExperienceRequired(request.getYearsExperienceRequired());
        }
        if (request.getLevel() != null) {
            entity.setLevel(request.getLevel());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (request.getType() != null) {
            entity.setType(request.getType());
        }
        if (request.getOpenings() != null) {
            entity.setOpenings(request.getOpenings());
        }
    }
}
