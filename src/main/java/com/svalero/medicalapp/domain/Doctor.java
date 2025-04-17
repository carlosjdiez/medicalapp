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
@Entity(name = "Doctor")
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String specialty;

    @Column
    private String email;

    @Column
    private boolean active;

    @Column(name = "years_experience")
    private int yearsExperience;

    @Column
    @OneToMany(mappedBy = "doctor")
    @JsonBackReference(value ="doctors_appointments")
    private List<Appointment> appointments;

}
