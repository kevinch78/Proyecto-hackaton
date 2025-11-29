package com.recruiter.backend.domain.service;

import com.recruiter.backend.domain.dtos.CandidateResponse;
import com.recruiter.backend.domain.dtos.CreateCandidateRequest;
import com.recruiter.backend.domain.dtos.UpdateCandidateRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public interface CandidateService {
    CandidateResponse create(CreateCandidateRequest request);
    Optional<CandidateResponse> findById(Long id);
    List<CandidateResponse> findAll();
    CandidateResponse update(Long id, UpdateCandidateRequest request);
    CandidateResponse uploadCv(Long candidateId, MultipartFile file);
    void delete(Long id);
}
