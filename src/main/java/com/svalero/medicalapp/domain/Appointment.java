package com.svalero.medicalapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Appointment")
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;  // Relación con Patient

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor; // Relación con Doctor

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column
    private String reason;

    @Column
    private String status;
}
