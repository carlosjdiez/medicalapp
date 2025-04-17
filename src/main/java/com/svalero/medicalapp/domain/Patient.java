package com.svalero.medicalapp.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients") // BBDD: Tabla "patients"

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column
    private boolean insured;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    @OneToMany(mappedBy = "patient")
    @JsonBackReference(value="patients_appointments")
    private List<Appointment> appointments;

}
