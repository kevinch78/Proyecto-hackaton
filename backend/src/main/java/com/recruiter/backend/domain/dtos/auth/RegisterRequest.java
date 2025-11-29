package com.recruiter.backend.domain.dtos.auth;

import com.recruiter.backend.infraestructure.entitys.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotEmpty
    private Set<UserEntity.Role> roles;
}
