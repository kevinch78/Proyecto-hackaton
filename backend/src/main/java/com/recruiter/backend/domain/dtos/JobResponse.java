package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.JobEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private Double salaryMin;
    private Double salaryMax;
    private List<String> requiredSkills;
    private List<String> requiredTechnologies;
    private Integer yearsExperienceRequired;
    private JobEntity.JobLevel level;
    private JobEntity.JobStatus status;
    private JobEntity.JobType type;
    private Integer openings;
    private Long companyId;
    private Long areaId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
