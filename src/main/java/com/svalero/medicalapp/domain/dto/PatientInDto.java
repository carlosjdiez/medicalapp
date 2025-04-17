package com.svalero.medicalapp.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientInDto {

    @NotNull (message = "El campo name es obligatorio")
    private String name;
    @NotNull (message = "El campo surname es obligatorio")
    private String surname;
    private String email;
    private LocalDate birthDate;
    private boolean insured;
    private String phoneNumber;


}




