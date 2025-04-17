package com.svalero.medicalapp.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class AppointmentInDto {

    @NotNull(message = "El campo dateTime es obligatorio")
    private LocalDate dateTime;
    @NotNull(message = "El campo reason es obligatorio")
    private String reason;
    private String status;



}
