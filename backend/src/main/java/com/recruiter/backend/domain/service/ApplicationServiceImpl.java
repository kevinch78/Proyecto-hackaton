package com.recruiter.backend.domain.service;

import com.recruiter.backend.domain.dtos.ApplicationResponse;
import com.recruiter.backend.domain.dtos.CreateApplicationRequest;
import com.recruiter.backend.domain.dtos.UpdateApplicationStatusRequest;
import com.recruiter.backend.domain.repository.ApplicationRepository;
import com.recruiter.backend.domain.repository.CandidateRepository;
import com.recruiter.backend.domain.repository.JobRepository;
import com.recruiter.backend.infraestructure.entitys.ApplicationEntity;
import com.recruiter.backend.infraestructure.entitys.CandidateEntity;
import com.recruiter.backend.infraestructure.entitys.JobEntity;
import com.recruiter.backend.infraestructure.mapper.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final ApplicationMapper applicationMapper;
    private final WowRiskAiService wowRiskAiService;

    @Override
    public ApplicationResponse create(CreateApplicationRequest request) {
        CandidateEntity candidate = candidateRepository.findById(request.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + request.getCandidateId()));
        
        JobEntity job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + request.getJobId()));

        // Optional: Check if application already exists
        // applicationRepository.findByCandidateIdAndJobId(candidate.getId(), job.getId()).ifPresent(a -> {
        //     throw new IllegalArgumentException("Candidate has already applied for this job.");
        // });

        ApplicationEntity newApplication = new ApplicationEntity();
        newApplication.setCandidate(candidate);
        newApplication.setJob(job);
        newApplication.setStatus(ApplicationEntity.ApplicationStatus.PENDING);
        
        // AI analysis would be triggered here
        ApplicationEntity analyzedApplication = wowRiskAiService.analyzeApplication(newApplication);
        
        ApplicationEntity savedApplication = applicationRepository.save(analyzedApplication);
        return applicationMapper.toResponse(savedApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationResponse> findById(Long id) {
        return applicationRepository.findById(id).map(applicationMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponse> findByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId).stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponse> findByCandidate(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId).stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponse updateStatus(Long id, UpdateApplicationStatusRequest request) {
        ApplicationEntity application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

        // Logic to update status and set timestamps
        application.setStatus(request.getStatus());
        application.setNotes(request.getNotes()); // Update notes
        
        switch (request.getStatus()) {
            case REVIEWING:
                application.setReviewedAt(LocalDateTime.now());
                break;
            case INTERVIEWING:
                application.setInterviewedAt(LocalDateTime.now());
                break;
            case HIRED:
                application.setHiredAt(LocalDateTime.now());
                // Further logic: update job status if filled, update candidate status, etc.
                break;
            case REJECTED:
                application.setRejectedAt(LocalDateTime.now());
                break;
            default:
                break;
        }

        ApplicationEntity updatedApplication = applicationRepository.update(application);
        return applicationMapper.toResponse(updatedApplication);
    }
}
