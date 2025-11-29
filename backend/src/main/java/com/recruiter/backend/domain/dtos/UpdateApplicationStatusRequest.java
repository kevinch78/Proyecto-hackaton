package com.recruiter.backend.domain.dtos;

import com.recruiter.backend.infraestructure.entitys.ApplicationEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApplicationStatusRequest {

    @NotNull
    private ApplicationEntity.ApplicationStatus status;

    private String notes; // Optional notes from the recruiter
}
