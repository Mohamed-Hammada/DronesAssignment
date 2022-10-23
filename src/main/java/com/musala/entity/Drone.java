package com.musala.entity;


import com.musala.entity.enums.DroneModel;
import com.musala.entity.enums.DroneState;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity
@Table
public class Drone {
    @Id
    @Column(nullable=false, length=100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @NotAudited
    @Column(nullable=false)
    private DroneModel model;

    @NotAudited
    @Range(min=0, max=500)
    @Column(nullable=false)
    private double currentWeight;

    @Column(precision=5, scale=2)
    private BigDecimal battery;

    @NotAudited
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    @Builder.Default
    private DroneState state = DroneState.IDLE;

    @NotAudited
    @Column(nullable=false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotAudited
    @Column(nullable=false, updatable=false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
