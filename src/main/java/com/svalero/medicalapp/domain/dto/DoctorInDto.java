package com.svalero.medicalapp.domain.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class DoctorInDto {

    @NotNull(message = "El campo name es obligatorio")
    private String name;
    private String surname;
    @NotNull (message = "El campo specialty es obligatorio")
    private String specialty;
    private String email;
    private boolean active;
    private int yearsExperience;

}
