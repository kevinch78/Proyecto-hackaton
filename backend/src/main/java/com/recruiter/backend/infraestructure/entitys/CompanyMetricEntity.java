package com.recruiter.backend.infraestructure.entitys;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "company_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyMetricEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetricType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double value = 0.0;

    @Column(nullable = false)
    private String unit; // %, USD, count, etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetricPeriod period = MetricPeriod.MONTHLY;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime recordedAt = LocalDateTime.now();

    public enum MetricType {
        FINANCIAL,          // Métricas financieras
        PERFORMANCE,        // Desempeño
        HIRING,            // Contratación
        RETENTION,         // Retención
        PRODUCTIVITY,      // Productividad
        COST              // Costos
    }

    public enum MetricPeriod {
        DAILY,
        WEEKLY,
        MONTHLY,
        QUARTERLY,
        YEARLY
    }
}
