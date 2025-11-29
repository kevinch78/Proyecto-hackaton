package com.recruiter.backend.domain.service.auth;

import com.recruiter.backend.domain.dtos.auth.AuthRequest;
import com.recruiter.backend.domain.dtos.auth.AuthResponse;
import com.recruiter.backend.domain.dtos.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
}
