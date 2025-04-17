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
@Entity(name = "Clinic")
@Table(name = "clinics")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column
    private String phone;

    @Column
    private boolean open;

    @Column
    @OneToMany(mappedBy = "clinics")
    @JsonBackReference(value ="clinics_appointments")
    private List<Appointment> appointment;
}
