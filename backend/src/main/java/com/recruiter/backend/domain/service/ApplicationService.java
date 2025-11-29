package com.recruiter.backend.domain.service;

import com.recruiter.backend.domain.dtos.ApplicationResponse;
import com.recruiter.backend.domain.dtos.CreateApplicationRequest;
import com.recruiter.backend.domain.dtos.UpdateApplicationStatusRequest;
import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    ApplicationResponse create(CreateApplicationRequest request);
    Optional<ApplicationResponse> findById(Long id);
    List<ApplicationResponse> findByJob(Long jobId);
    List<ApplicationResponse> findByCandidate(Long candidateId);
    ApplicationResponse updateStatus(Long id, UpdateApplicationStatusRequest request);
}
