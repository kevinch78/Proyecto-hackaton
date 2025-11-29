package com.recruiter.backend.controller;

import com.recruiter.backend.domain.dtos.AreaResponse;
import com.recruiter.backend.domain.dtos.CreateAreaRequest;
import com.recruiter.backend.domain.dtos.UpdateAreaRequest;
import com.recruiter.backend.domain.service.AreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/areas")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    // Note: Creating an area requires a companyId in the request body.
    // Another approach is POST /api/companies/{companyId}/areas, but this is simpler for now.
    @PostMapping
    public ResponseEntity<AreaResponse> create(@Valid @RequestBody CreateAreaRequest request) {
        AreaResponse response = areaService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaResponse> getById(@PathVariable Long id) {
        return areaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/areas?companyId={id}
    @GetMapping
    public ResponseEntity<List<AreaResponse>> getAllByCompany(@RequestParam Long companyId) {
        return ResponseEntity.ok(areaService.findAllByCompany(companyId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateAreaRequest request) {
        try {
            return ResponseEntity.ok(areaService.update(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            areaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
