package com.recruiter.backend.domain.service;

import com.recruiter.backend.domain.dtos.JobResponse;
import com.recruiter.backend.domain.dtos.CreateJobRequest;
import com.recruiter.backend.domain.dtos.UpdateJobRequest;

import java.util.List;
import java.util.Optional;

public interface JobService {
    JobResponse create(CreateJobRequest request);
    Optional<JobResponse> findById(Long id);
    List<JobResponse> findAllByCompany(Long companyId);
    List<JobResponse> findAllByArea(Long areaId);
    JobResponse update(Long id, UpdateJobRequest request);
    void delete(Long id);
}
