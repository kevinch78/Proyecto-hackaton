package com.recruiter.backend.infraestructure.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_analysis_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiAnalysisLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The application this analysis was for
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private ApplicationEntity application;

    // Inputs to the AI model
    @Lob
    @Column(columnDefinition = "TEXT")
    private String modelInputs; // JSON string of all inputs used

    // Raw output from the model
    @Lob
    @Column(columnDefinition = "TEXT")
    private String modelOutput; // Raw JSON or text from the model

    // Calculated results
    private Double matchScore;
    private Double financialRisk;
    private Double areaImpact;

    // Version of the model used
    private String modelVersion;

    @Column(nullable = false, updatable = false)
    private LocalDateTime analysisTimestamp = LocalDateTime.now();
}
