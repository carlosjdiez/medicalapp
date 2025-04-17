package com.svalero.medicalapp.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class AppointmentInDto {

    @NotNull(message = "El campo dateTime es obligatorio")
    private LocalDateTime dateTime;
    @NotNull(message = "El campo reason es obligatorio")
    private String reason;
    private String status;



}
