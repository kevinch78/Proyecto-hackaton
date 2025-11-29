package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.JobEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 2000)
    private String description;

    @NotNull(message = "Minimum salary cannot be null")
    @Min(0)
    private Double salaryMin;

    @NotNull(message = "Maximum salary cannot be null")
    @Min(0)
    private Double salaryMax;

    private List<String> requiredSkills;

    private List<String> requiredTechnologies;

    @NotNull(message = "Years of experience required cannot be null")
    @Min(0)
    private Integer yearsExperienceRequired;

    @NotNull
    private JobEntity.JobLevel level;

    @NotNull
    private JobEntity.JobType type;

    @NotNull
    @Min(1)
    private Integer openings;

    @NotNull
    private Long companyId;

    @NotNull
    private Long areaId;
}
