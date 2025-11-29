package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private Long id;
    private String name;
    private String sector;
    private CompanyEntity.CompanySize size;
    private Double totalPersonnelBudget;
    private Double usedPersonnelBudget;
    private String country;
    private String city;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
