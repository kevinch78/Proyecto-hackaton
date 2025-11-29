package com.recruiter.backend.controller;

import com.recruiter.backend.domain.dtos.CompanyResponse;
import com.recruiter.backend.domain.dtos.CreateCompanyRequest;
import com.recruiter.backend.domain.dtos.UpdateCompanyRequest;
import com.recruiter.backend.domain.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CreateCompanyRequest request) {
        CompanyResponse response = companyService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable Long id) {
        return companyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateCompanyRequest request) {
        try {
            return ResponseEntity.ok(companyService.update(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            companyService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
