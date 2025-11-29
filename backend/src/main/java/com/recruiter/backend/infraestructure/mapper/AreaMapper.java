package com.recruiter.backend.infraestructure.mapper;

import com.recruiter.backend.domain.dtos.AreaResponse;
import com.recruiter.backend.domain.dtos.CreateAreaRequest;
import com.recruiter.backend.domain.dtos.UpdateAreaRequest;
import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {

    public AreaResponse toResponse(AreaEntity entity) {
        if (entity == null) return null;
        return new AreaResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getBudgetAllocated(),
                entity.getBudgetUsed(),
                entity.getCurrentEmployees(),
                entity.getMaxEmployees(),
                entity.getPerformanceScore(),
                entity.getTurnoverRate(),
                entity.getHiringPriority(),
                entity.getCompany().getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public AreaEntity toEntity(CreateAreaRequest request, CompanyEntity company) {
        if (request == null) return null;
        AreaEntity entity = new AreaEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setBudgetAllocated(request.getBudgetAllocated());
        entity.setMaxEmployees(request.getMaxEmployees());
        entity.setHiringPriority(request.getHiringPriority());
        entity.setCompany(company);
        // Set default values
        entity.setBudgetUsed(0.0);
        entity.setCurrentEmployees(0);
        entity.setPerformanceScore(0.0);
        entity.setTurnoverRate(0.0);
        return entity;
    }

    public void updateEntityFromRequest(UpdateAreaRequest request, AreaEntity entity) {
        if (request == null || entity == null) return;

        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
        if (request.getBudgetAllocated() != null) {
            entity.setBudgetAllocated(request.getBudgetAllocated());
        }
        if (request.getMaxEmployees() != null) {
            entity.setMaxEmployees(request.getMaxEmployees());
        }
        if (request.getHiringPriority() != null) {
            entity.setHiringPriority(request.getHiringPriority());
        }
    }
}
