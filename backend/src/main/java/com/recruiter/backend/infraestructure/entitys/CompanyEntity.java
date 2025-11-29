package com.recruiter.backend.infraestructure.entitys;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String sector;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanySize size;

    @Column(nullable = false)
    private Double totalPersonnelBudget = 0.0;

    @Column(nullable = false)
    private Double usedPersonnelBudget = 0.0;

    private String country;

    private String city;

    @Column(length = 500)
    private String description;

    // Relación con áreas
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AreaEntity> areas = new ArrayList<>();

    // Relación con vacantes
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobEntity> jobs = new ArrayList<>();

    // Relación con métricas
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompanyMetricEntity> metrics = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum CompanySize {
        SMALL,      // 1-50
        MEDIUM,     // 51-250
        LARGE,      // 251-1000
        ENTERPRISE  // 1000+
    }

    // Método helper para calcular presupuesto disponible
    public Double getAvailableBudget() {
        return totalPersonnelBudget - usedPersonnelBudget;
    }
}
