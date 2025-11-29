package com.recruiter.backend.controller;

import com.recruiter.backend.domain.dtos.JobResponse;
import com.recruiter.backend.domain.dtos.CreateJobRequest;
import com.recruiter.backend.domain.dtos.UpdateJobRequest;
import com.recruiter.backend.domain.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse> create(@Valid @RequestBody CreateJobRequest request) {
        JobResponse response = jobService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getById(@PathVariable Long id) {
        return jobService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/jobs?companyId={id} or /api/jobs?areaId={id}
    @GetMapping
    public ResponseEntity<List<JobResponse>> getAll(
            @RequestParam Optional<Long> companyId,
            @RequestParam Optional<Long> areaId) {
        
        if (companyId.isPresent()) {
            return ResponseEntity.ok(jobService.findAllByCompany(companyId.get()));
        }
        if (areaId.isPresent()) {
            return ResponseEntity.ok(jobService.findAllByArea(areaId.get()));
        }
        // Decide on default behavior: maybe return all jobs, or an empty list, or bad request.
        // Returning empty list is safe.
        return ResponseEntity.ok(List.of());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateJobRequest request) {
        try {
            return ResponseEntity.ok(jobService.update(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            jobService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
