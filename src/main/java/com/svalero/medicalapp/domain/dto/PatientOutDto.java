package com.svalero.medicalapp.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientOutDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate registrationDate;
    private boolean insured;
    private String phoneNumber;

}


