package com.svalero.medicalapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class DoctorOutDto {

    private long id;
    private String name;
    private String surname;
    private String specialty;
    private String email;
    private boolean active;
    private int yearsExperience;

}
