package com.svalero.medicalapp.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients") // BBDD: Tabla "patients"

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false) // Obligatorio
    private String name;

    @Column(nullable = false) // Obligatorio
    private String surname;

    @Column(nullable = false, unique = true) // Email único
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false) // Obligatorio
    private boolean insured;

    @Column(name = "phone_number")
    private String phoneNumber;

}
