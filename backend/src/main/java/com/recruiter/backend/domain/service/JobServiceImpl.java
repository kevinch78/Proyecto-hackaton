package com.recruiter.backend.domain.service;

import com.recruiter.backend.domain.dtos.JobResponse;
import com.recruiter.backend.domain.dtos.CreateJobRequest;
import com.recruiter.backend.domain.dtos.UpdateJobRequest;
import com.recruiter.backend.domain.repository.AreaRepository;
import com.recruiter.backend.domain.repository.CompanyRepository;
import com.recruiter.backend.domain.repository.JobRepository;
import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import com.recruiter.backend.infraestructure.entitys.JobEntity;
import com.recruiter.backend.infraestructure.mapper.JobMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final AreaRepository areaRepository;
    private final JobMapper jobMapper;

    @Override
    public JobResponse create(CreateJobRequest request) {
        CompanyEntity company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + request.getCompanyId()));

        AreaEntity area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new RuntimeException("Area not found with id: " + request.getAreaId()));
        
        // Validate that the area belongs to the company
        if (!area.getCompany().getId().equals(company.getId())) {
            throw new IllegalArgumentException("Area with id " + area.getId() + " does not belong to company with id " + company.getId());
        }

        JobEntity entity = jobMapper.toEntity(request, company, area);
        JobEntity savedEntity = jobRepository.save(entity);
        return jobMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JobResponse> findById(Long id) {
        return jobRepository.findById(id).map(jobMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobResponse> findAllByCompany(Long companyId) {
        return jobRepository.findByCompanyId(companyId).stream()
                .map(jobMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobResponse> findAllByArea(Long areaId) {
        return jobRepository.findByAreaId(areaId).stream()
                .map(jobMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse update(Long id, UpdateJobRequest request) {
        JobEntity existingEntity = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        jobMapper.updateEntityFromRequest(request, existingEntity);
        JobEntity updatedEntity = jobRepository.update(existingEntity);
        return jobMapper.toResponse(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        if (!jobRepository.findById(id).isPresent()) {
            throw new RuntimeException("Job not found with id: " + id);
        }
        jobRepository.deleteById(id);
    }
}
