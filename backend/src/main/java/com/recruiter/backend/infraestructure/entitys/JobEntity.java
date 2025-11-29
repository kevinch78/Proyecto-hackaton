package com.recruiter.backend.infraestructure.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(nullable = false)
    private Double salaryMin = 0.0;

    @Column(nullable = false)
    private Double salaryMax = 0.0;

    @ElementCollection
    @CollectionTable(name = "job_required_skills", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "skill")
    private List<String> requiredSkills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "job_required_technologies", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "technology")
    private List<String> requiredTechnologies = new ArrayList<>();

    @Column(nullable = false)
    private Integer yearsExperienceRequired = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobLevel level = JobLevel.JUNIOR;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status = JobStatus.OPEN;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType type = JobType.FULL_TIME;

    @Column(nullable = false)
    private Integer openings = 1;

    // Relación con empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    // Relación con área
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private AreaEntity area;

    // Relación con aplicaciones
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationEntity> applications = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDateTime closedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum JobLevel {
        JUNIOR,
        SEMI_SENIOR,
        SENIOR,
        LEAD,
        MANAGER
    }

    public enum JobStatus {
        DRAFT,
        OPEN,
        PAUSED,
        CLOSED,
        FILLED
    }

    public enum JobType {
        FULL_TIME,
        PART_TIME,
        CONTRACT,
        FREELANCE
    }

    // Helper para verificar si está en rango salarial
    public boolean isInSalaryRange(Double salary) {
        return salary >= salaryMin && salary <= salaryMax;
    }
}