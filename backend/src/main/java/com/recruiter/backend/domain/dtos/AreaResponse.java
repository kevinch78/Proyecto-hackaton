package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaResponse {
    private Long id;
    private String name;
    private String description;
    private Double budgetAllocated;
    private Double budgetUsed;
    private Integer currentEmployees;
    private Integer maxEmployees;
    private Double performanceScore;
    private Double turnoverRate;
    private AreaEntity.HiringPriority hiringPriority;
    private Long companyId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
