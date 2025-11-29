package com.recruiter.backend.domain.dtos;

import jakarta.validation.constraints.Email;
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
public class CreateCandidateRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    @NotNull(message = "Requested salary cannot be null")
    @Min(value = 0, message = "Requested salary must be positive")
    private Double requestedSalary;

    @NotNull(message = "Years of experience cannot be null")
    @Min(value = 0, message = "Years of experience must be positive")
    private Integer yearsOfExperience;

    private List<String> skills;

    private List<String> technologies;
}
