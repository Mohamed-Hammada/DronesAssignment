package com.musala.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Medication {
    @Id
    @Pattern(regexp="[A-Z0-9\\_]+")
    @Column(nullable=false,length=32)
    private String code;

    @Pattern(regexp="[a-zA-Z0-9\\_\\-]+")
    @Column(nullable=false,length=32)
    private String name;

    @Column(nullable=false,length=32)
    private double weight;

    @Column(nullable=false,length=1024)
    private String image;

    @Column(nullable=false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable=false, updatable=false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
