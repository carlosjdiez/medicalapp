package com.svalero.medicalapp.domain.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppointmentOutDto {

    private long id;
    private LocalDate dateTime;
    private String reason;
    private String status;
    private int patientId;  // Relación con Patient

}
