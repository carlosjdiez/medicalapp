package com.svalero.medicalapp.domain.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppointmentOutDto {

    private long id;
    private int patientId;  // Relación con Patient
    private int doctorId; // Relación con Doctor
    private LocalDateTime dateTime;
    private String reason;
    private String status;


}
