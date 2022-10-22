package com.musala.entity;


import com.musala.entity.enums.DroneModel;
import com.musala.entity.enums.DroneState;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Drone {
    @Id
    @Column(nullable=false, length=100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)

    @Column(nullable=false)
    private DroneModel model;

    @Range(min=0, max=500)
    @Column(nullable=false)
    private double currentWeight;

    @Column(precision=5, scale=2)
    private BigDecimal battery;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    @Builder.Default
    private DroneState state = DroneState.IDLE;

    @Column(nullable=false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable=false, updatable=false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
