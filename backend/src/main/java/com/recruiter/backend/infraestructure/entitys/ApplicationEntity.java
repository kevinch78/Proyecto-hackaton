package com.recruiter.backend.infraestructure.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con candidato
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateEntity candidate;

    // Relación con vacante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private JobEntity job;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    // Scores y análisis
    @Column(nullable = false)
    private Double matchScore = 0.0; // Score de match con la vacante (0-100)

    @Column(nullable = false)
    private Double financialRisk = 0.0; // Riesgo financiero (0-100)

    @Column(nullable = false)
    private Double areaImpact = 0.0; // Impacto en el área (0-100)

    @Lob
    @Column(columnDefinition = "TEXT")
    private String aiAnalysis; // Análisis detallado de la IA

    @Lob
    @Column(columnDefinition = "TEXT")
    private String aiRecommendation; // Recomendación de la IA

    @Column(length = 1000)
    private String notes; // Notas del reclutador

    @Column(nullable = false, updatable = false)
    private LocalDateTime appliedAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDateTime reviewedAt;

    private LocalDateTime interviewedAt;

    private LocalDateTime hiredAt;

    private LocalDateTime rejectedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum ApplicationStatus {
        PENDING,        // Recién aplicado
        REVIEWING,      // En revisión
        SHORTLISTED,    // Pre-seleccionado
        INTERVIEWING,   // En entrevista
        OFFER,          // Oferta enviada
        HIRED,          // Contratado
        REJECTED        // Rechazado
    }
}
