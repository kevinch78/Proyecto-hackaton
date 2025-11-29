package com.recruiter.backend.controller;

import com.recruiter.backend.domain.dtos.CandidateResponse;
import com.recruiter.backend.domain.dtos.CreateCandidateRequest;
import com.recruiter.backend.domain.dtos.UpdateCandidateRequest;
import com.recruiter.backend.domain.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateResponse> create(@Valid @RequestBody CreateCandidateRequest request) {
        CandidateResponse response = candidateService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getById(@PathVariable Long id) {
        return candidateService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAll() {
        return ResponseEntity.ok(candidateService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateCandidateRequest request) {
        try {
            CandidateResponse updatedResponse = candidateService.update(id, request);
            return ResponseEntity.ok(updatedResponse);
        } catch (RuntimeException e) {
            // A more specific exception would be better
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            candidateService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/upload-cv")
    public ResponseEntity<CandidateResponse> uploadCv(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            CandidateResponse updatedCandidate = candidateService.uploadCv(id, file);
            return ResponseEntity.ok(updatedCandidate);
        } catch (RuntimeException e) {
            // More specific exception handling would be good (e.g., file type validation)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Or a custom error response
        }

    }
    

}