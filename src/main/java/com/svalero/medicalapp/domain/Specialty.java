package com.svalero.medicalapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Specialty")
@Table(name = "specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "requires_referral")
    private boolean requiresReferral;

    @Column(name = "average_duration")
    private int averageDuration; // Minimo 10

    @Column
    private boolean active;

    @Column
    @OneToMany(mappedBy = "specialties")
    @JsonBackReference(value ="specialties_appointments")
    private List<Appointment> appointment;
}
