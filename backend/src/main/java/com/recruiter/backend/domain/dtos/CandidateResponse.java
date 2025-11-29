package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.CandidateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Double scoreIa;
    private Double requestedSalary;
    private List<String> skills;
    private List<String> technologies;
    private Integer yearsOfExperience;
    private CandidateEntity.CandidateStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}