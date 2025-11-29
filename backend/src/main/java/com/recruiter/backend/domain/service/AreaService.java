package com.recruiter.backend.domain.service;

import com.recruiter.backend.domain.dtos.AreaResponse;
import com.recruiter.backend.domain.dtos.CreateAreaRequest;
import com.recruiter.backend.domain.dtos.UpdateAreaRequest;

import java.util.List;
import java.util.Optional;

public interface AreaService {
    AreaResponse create(CreateAreaRequest request);
    Optional<AreaResponse> findById(Long id);
    List<AreaResponse> findAllByCompany(Long companyId);
    AreaResponse update(Long id, UpdateAreaRequest request);
    void delete(Long id);
}
