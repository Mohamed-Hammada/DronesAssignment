package com.musala.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class LoadMedication {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String sourceLocation;

    @Column(nullable=false)
    private String destinationLocation;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="drone_serial_number", referencedColumnName="serialNumber")
    private Drone drone;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="medication_code", referencedColumnName="code", unique=true)
    private Medication medication;

    @Column(nullable=false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable=false, updatable=false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
