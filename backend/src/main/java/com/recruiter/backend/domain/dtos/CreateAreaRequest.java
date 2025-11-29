package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAreaRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Min(0)
    private Double budgetAllocated;

    @NotNull
    @Min(0)
    private Integer maxEmployees;

    @NotNull
    private AreaEntity.HiringPriority hiringPriority;

    @NotNull
    private Long companyId;
}
