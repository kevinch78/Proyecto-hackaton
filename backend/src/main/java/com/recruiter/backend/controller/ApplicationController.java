package com.recruiter.backend.controller;

import com.recruiter.backend.domain.dtos.ApplicationResponse;
import com.recruiter.backend.domain.dtos.CreateApplicationRequest;
import com.recruiter.backend.domain.dtos.UpdateApplicationStatusRequest;
import com.recruiter.backend.domain.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse> create(@Valid @RequestBody CreateApplicationRequest request) {
        ApplicationResponse response = applicationService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getById(@PathVariable Long id) {
        return applicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/applications?jobId={id} or /api/applications?candidateId={id}
    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAll(
            @RequestParam Optional<Long> jobId,
            @RequestParam Optional<Long> candidateId) {
        
        if (jobId.isPresent()) {
            return ResponseEntity.ok(applicationService.findByJob(jobId.get()));
        }
        if (candidateId.isPresent()) {
            return ResponseEntity.ok(applicationService.findByCandidate(candidateId.get()));
        }
        return ResponseEntity.ok(List.of());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateApplicationStatusRequest request) {
        try {
            return ResponseEntity.ok(applicationService.updateStatus(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
