package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyRequest {

    private String name;

    private String sector;

    private CompanyEntity.CompanySize size;

    @Min(0)
    private Double totalPersonnelBudget;

    @Min(0)
    private Double usedPersonnelBudget;

    private String country;

    private String city;

    private String description;
}
