package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.CandidateEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCandidateRequest {

    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    private String phone;

    @Min(value = 0, message = "Requested salary must be positive")
    private Double requestedSalary;

    @Min(value = 0, message = "Years of experience must be positive")
    private Integer yearsOfExperience;

    private List<String> skills;

    private List<String> technologies;
    
    private CandidateEntity.CandidateStatus status;
}
