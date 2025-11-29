package com.recruiter.backend.domain.service;

import com.recruiter.backend.domain.dtos.CompanyResponse;
import com.recruiter.backend.domain.dtos.CreateCompanyRequest;
import com.recruiter.backend.domain.dtos.UpdateCompanyRequest;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyResponse create(CreateCompanyRequest request);
    Optional<CompanyResponse> findById(Long id);
    List<CompanyResponse> findAll();
    CompanyResponse update(Long id, UpdateCompanyRequest request);
    void delete(Long id);
}
