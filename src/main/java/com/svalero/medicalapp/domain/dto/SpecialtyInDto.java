package com.svalero.medicalapp.domain.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class SpecialtyInDto {

    @NotNull(message = "El campo name es obligatorio")
    private String name;
    @NotNull (message = "El campo description es obligatorio")
    private String description;
    private boolean requiresReferral;
    private int averageDuration;
    private boolean active;

}
