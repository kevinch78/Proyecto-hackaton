package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAreaRequest {

    private String name;

    private String description;

    @Min(0)
    private Double budgetAllocated;

    @Min(0)
    private Integer maxEmployees;
    
    private AreaEntity.HiringPriority hiringPriority;
}
