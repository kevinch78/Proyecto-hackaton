package com.recruiter.backend.domain.service;

import com.recruiter.backend.domain.dtos.CompanyResponse;
import com.recruiter.backend.domain.dtos.CreateCompanyRequest;
import com.recruiter.backend.domain.dtos.UpdateCompanyRequest;
import com.recruiter.backend.domain.repository.CompanyRepository;
import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import com.recruiter.backend.infraestructure.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponse create(CreateCompanyRequest request) {
        companyRepository.findByName(request.getName()).ifPresent(c -> {
            throw new IllegalArgumentException("Company with name " + request.getName() + " already exists.");
        });

        CompanyEntity entity = companyMapper.toEntity(request);
        CompanyEntity savedEntity = companyRepository.save(entity);
        return companyMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyResponse> findById(Long id) {
        return companyRepository.findById(id).map(companyMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> findAll() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponse update(Long id, UpdateCompanyRequest request) {
        CompanyEntity existingEntity = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));

        if (request.getName() != null && !request.getName().equals(existingEntity.getName())) {
            companyRepository.findByName(request.getName()).ifPresent(c -> {
                throw new IllegalArgumentException("Company name " + request.getName() + " is already in use.");
            });
        }

        companyMapper.updateEntityFromRequest(request, existingEntity);
        CompanyEntity updatedEntity = companyRepository.update(existingEntity);
        return companyMapper.toResponse(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        if (!companyRepository.findById(id).isPresent()) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }
}
