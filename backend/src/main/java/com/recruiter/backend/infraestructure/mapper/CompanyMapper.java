package com.recruiter.backend.infraestructure.mapper;

import com.recruiter.backend.domain.dtos.CompanyResponse;
import com.recruiter.backend.domain.dtos.CreateCompanyRequest;
import com.recruiter.backend.domain.dtos.UpdateCompanyRequest;
import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse toResponse(CompanyEntity entity) {
        if (entity == null) return null;
        return new CompanyResponse(
                entity.getId(),
                entity.getName(),
                entity.getSector(),
                entity.getSize(),
                entity.getTotalPersonnelBudget(),
                entity.getUsedPersonnelBudget(),
                entity.getCountry(),
                entity.getCity(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public CompanyEntity toEntity(CreateCompanyRequest request) {
        if (request == null) return null;
        CompanyEntity entity = new CompanyEntity();
        entity.setName(request.getName());
        entity.setSector(request.getSector());
        entity.setSize(request.getSize());
        entity.setTotalPersonnelBudget(request.getTotalPersonnelBudget());
        entity.setCountry(request.getCountry());
        entity.setCity(request.getCity());
        entity.setDescription(request.getDescription());
        // Set default values
        entity.setUsedPersonnelBudget(0.0);
        return entity;
    }

    public void updateEntityFromRequest(UpdateCompanyRequest request, CompanyEntity entity) {
        if (request == null || entity == null) return;

        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getSector() != null) {
            entity.setSector(request.getSector());
        }
        if (request.getSize() != null) {
            entity.setSize(request.getSize());
        }
        if (request.getTotalPersonnelBudget() != null) {
            entity.setTotalPersonnelBudget(request.getTotalPersonnelBudget());
        }
        if (request.getUsedPersonnelBudget() != null) {
            entity.setUsedPersonnelBudget(request.getUsedPersonnelBudget());
        }
        if (request.getCountry() != null) {
            entity.setCountry(request.getCountry());
        }
        if (request.getCity() != null) {
            entity.setCity(request.getCity());
        }
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
    }
}
