package com.recruiter.backend.domain.service;

import com.recruiter.backend.domain.dtos.AreaResponse;
import com.recruiter.backend.domain.dtos.CreateAreaRequest;
import com.recruiter.backend.domain.dtos.UpdateAreaRequest;
import com.recruiter.backend.domain.repository.AreaRepository;
import com.recruiter.backend.domain.repository.CompanyRepository;
import com.recruiter.backend.infraestructure.entitys.AreaEntity;
import com.recruiter.backend.infraestructure.entitys.CompanyEntity;
import com.recruiter.backend.infraestructure.mapper.AreaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final CompanyRepository companyRepository;
    private final AreaMapper areaMapper;

    @Override
    public AreaResponse create(CreateAreaRequest request) {
        CompanyEntity company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + request.getCompanyId()));

        AreaEntity entity = areaMapper.toEntity(request, company);
        AreaEntity savedEntity = areaRepository.save(entity);
        return areaMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreaResponse> findById(Long id) {
        return areaRepository.findById(id).map(areaMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AreaResponse> findAllByCompany(Long companyId) {
        return areaRepository.findByCompanyId(companyId).stream()
                .map(areaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AreaResponse update(Long id, UpdateAreaRequest request) {
        AreaEntity existingEntity = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Area not found with id: " + id));

        areaMapper.updateEntityFromRequest(request, existingEntity);
        AreaEntity updatedEntity = areaRepository.update(existingEntity);
        return areaMapper.toResponse(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        if (!areaRepository.findById(id).isPresent()) {
            throw new RuntimeException("Area not found with id: " + id);
        }
        areaRepository.deleteById(id);
    }
}
