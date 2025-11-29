package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String sector;

    @NotNull
    private CompanyEntity.CompanySize size;

    @NotNull
    @Min(0)
    private Double totalPersonnelBudget;

    private String country;

    private String city;

    private String description;
}
