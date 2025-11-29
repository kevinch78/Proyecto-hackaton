package com.recruiter.backend.infraestructure.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String cvText;

    private String cvPath;

    @Column(nullable = false)
    private Double scoreIa = 0.0;

    @Column(nullable = false)
    private Double requestedSalary = 0.0;

    @ElementCollection
    @CollectionTable(name = "candidate_skills", joinColumns = @JoinColumn(name = "candidate_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "candidate_technologies", joinColumns = @JoinColumn(name = "candidate_id"))
    @Column(name = "technology")
    private List<String> technologies = new ArrayList<>();

    @Column(nullable = false)
    private Integer yearsOfExperience = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CandidateStatus status = CandidateStatus.ACTIVE;

    // Relación con aplicaciones a vacantes
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationEntity> applications = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at", nullable = true, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    public enum CandidateStatus {
        ACTIVE,
        HIRED,
        REJECTED,
        INACTIVE
    }
}
