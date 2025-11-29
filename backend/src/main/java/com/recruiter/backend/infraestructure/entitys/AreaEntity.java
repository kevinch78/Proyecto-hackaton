package com.recruiter.backend.infraestructure.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "areas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Double budgetAllocated = 0.0;

    @Column(nullable = false)
    private Double budgetUsed = 0.0;

    @Column(nullable = false)
    private Integer currentEmployees = 0;

    @Column(nullable = false)
    private Integer maxEmployees = 0;

    // Métricas de desempeño del área
    @Column(nullable = false)
    private Double performanceScore = 0.0; // 0-100

    @Column(nullable = false)
    private Double turnoverRate = 0.0; // Porcentaje de rotación

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HiringPriority hiringPriority = HiringPriority.MEDIUM;

    // Relación con empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    // Relación con vacantes
    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobEntity> jobs = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum HiringPriority {
        LOW,
        MEDIUM,
        HIGH,
        URGENT
    }

    // Método helper para calcular presupuesto disponible
    public Double getAvailableBudget() {
        return budgetAllocated - budgetUsed;
    }

    // Método helper para calcular si puede contratar
    public boolean canHire(Double salary) {
        return getAvailableBudget() >= salary && currentEmployees < maxEmployees;
    }
}