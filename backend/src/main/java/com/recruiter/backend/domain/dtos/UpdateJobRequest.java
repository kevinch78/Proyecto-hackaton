package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.JobEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobRequest {

    @Size(max = 255)
    private String title;

    @Size(max = 2000)
    private String description;

    @Min(0)
    private Double salaryMin;

    @Min(0)
    private Double salaryMax;

    private List<String> requiredSkills;

    private List<String> requiredTechnologies;

    @Min(0)
    private Integer yearsExperienceRequired;

    private JobEntity.JobLevel level;

    private JobEntity.JobStatus status;

    private JobEntity.JobType type;

    @Min(1)
    private Integer openings;
}
