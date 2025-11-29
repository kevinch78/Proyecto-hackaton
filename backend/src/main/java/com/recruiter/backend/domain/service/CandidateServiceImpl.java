package com.recruiter.backend.domain.service;

import com.recruiter.backend.application.services.CvProcessingService;
import com.recruiter.backend.domain.dtos.CandidateResponse;
import com.recruiter.backend.domain.dtos.CreateCandidateRequest;
import com.recruiter.backend.domain.dtos.CvContentDto;
import com.recruiter.backend.domain.dtos.UpdateCandidateRequest;
import com.recruiter.backend.domain.repository.CandidateRepository;
import com.recruiter.backend.infraestructure.entitys.CandidateEntity;
import com.recruiter.backend.infraestructure.mapper.CandidateMapper;
import com.recruiter.backend.infraestructure.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;
    private final FileStorageService fileStorageService;
    private final CvProcessingService cvProcessingService;

    @Override
    public CandidateResponse create(CreateCandidateRequest request) {
        // Optional: Check if candidate with the same email already exists
        candidateRepository.findByEmail(request.getEmail()).ifPresent(c -> {
            throw new IllegalArgumentException("Candidate with email " + request.getEmail() + " already exists.");
        });

        CandidateEntity entity = candidateMapper.toEntity(request);
        CandidateEntity savedEntity = candidateRepository.save(entity);
        return candidateMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CandidateResponse> findById(Long id) {
        return candidateRepository.findById(id).map(candidateMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateResponse> findAll() {
        return candidateRepository.findAll().stream()
                .map(candidateMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateResponse update(Long id, UpdateCandidateRequest request) {
        CandidateEntity existingEntity = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + id));

        // Optional: Check for email uniqueness if it's being changed
        if (request.getEmail() != null && !request.getEmail().equals(existingEntity.getEmail())) {
            candidateRepository.findByEmail(request.getEmail()).ifPresent(c -> {
                throw new IllegalArgumentException("Email " + request.getEmail() + " is already in use.");
            });
        }

        candidateMapper.updateEntityFromRequest(request, existingEntity);
        CandidateEntity updatedEntity = candidateRepository.update(existingEntity);
        return candidateMapper.toResponse(updatedEntity);
    }

    @Override
    public CandidateResponse uploadCv(Long candidateId, MultipartFile file) {
        CandidateEntity candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));

        try {
            // Store the file
            String fileName = fileStorageService.storeFile(file);
            candidate.setCvPath(fileName); // Save the path to the entity

            // Process the CV
            Path filePath = fileStorageService.loadFile(fileName);
            CvContentDto cvContent = cvProcessingService.processCv(filePath);

            candidate.setCvText(cvContent.getFullText());
            candidate.setSkills(cvContent.getExtractedSkills().stream().toList());
            candidate.setTechnologies(cvContent.getExtractedTechnologies().stream().toList());
            candidate.setYearsOfExperience(cvContent.getYearsOfExperience());

            CandidateEntity updatedCandidate = candidateRepository.update(candidate);
            return candidateMapper.toResponse(updatedCandidate);

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload and process CV for candidate " + candidateId + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        if (!candidateRepository.findById(id).isPresent()) {
            throw new RuntimeException("Candidate not found with id: " + id);
        }
        candidateRepository.deleteById(id);
    }
}
