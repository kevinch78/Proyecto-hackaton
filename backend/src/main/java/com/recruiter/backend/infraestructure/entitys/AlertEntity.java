package com.recruiter.backend.infraestructure.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertStatus status = AlertStatus.UNREAD;

    // Link to the entity that triggered the alert (optional)
    // Using generic fields for simplicity, a more complex setup might use @Any or separate tables
    private Long relatedEntityId;
    private String relatedEntityType;

    // User this alert is for (can be null if it's a general alert)
    // In the future, this would link to a UserEntity
    private Long userId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime readAt;

    public enum AlertType {
        RISK_HIGH,          // Candidato con alto riesgo
        BUDGET_EXCEEDED,    // Presupuesto de área excedido
        NEW_CANDIDATE,      // Nuevo candidato para una vacante importante
        INTERVIEW_SCHEDULED // Recordatorio de entrevista
    }

    public enum AlertStatus {
        UNREAD,
        READ,
        ARCHIVED
    }

    @PreUpdate
    protected void onUpdate() {
        if (status == AlertStatus.READ && readAt == null) {
            readAt = LocalDateTime.now();
        }
    }
}
