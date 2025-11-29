package com.recruiter.backend.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicationRequest {

    @NotNull
    private Long candidateId;

    @NotNull
    private Long jobId;
}
