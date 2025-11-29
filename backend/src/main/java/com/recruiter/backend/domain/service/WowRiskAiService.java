package com.recruiter.backend.domain.service;

import com.recruiter.backend.infraestructure.entitys.ApplicationEntity;

public interface WowRiskAiService {
    ApplicationEntity analyzeApplication(ApplicationEntity application);
}
