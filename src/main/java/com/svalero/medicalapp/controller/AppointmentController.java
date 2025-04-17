package com.svalero.medicalapp.controller;


import com.svalero.medicalapp.domain.dto.AppointmentInDto;
import com.svalero.medicalapp.domain.dto.AppointmentOutDto;
import com.svalero.medicalapp.domain.dto.ErrorResponse;
import com.svalero.medicalapp.exception.AppointmentNotFoundException;
import com.svalero.medicalapp.exception.PatientNotFoundException;
import com.svalero.medicalapp.service.AppointmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    private final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    // Buscar appointments
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentOutDto>> getAll(@RequestParam(value = "dateTime", defaultValue = "") LocalDate dateTime,
                                                          @RequestParam(value = "reason", defaultValue = "") String reason,
                                                          @RequestParam(value = "status", defaultValue = "") String status) {
        logger.info("BEGIN getAll");
        List<AppointmentOutDto> appointments = appointmentService.getAll(dateTime, reason, status);
        logger.info("END getAll");
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("/patients/:patientId/appointments")
    public ResponseEntity<AppointmentOutDto> addAppointment(long patientId, @Valid @RequestBody AppointmentInDto appointment) throws PatientNotFoundException {
        logger.info("BEGIN addAppointment");
        AppointmentOutDto newAppointment = appointmentService.add(patientId, appointment);
        logger.info("END addAppointment");
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }


    // Error 404
    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCarNotFoundException(AppointmentNotFoundException exception) {
        ErrorResponse error = ErrorResponse.generalError(404, exception.getMessage());
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Error 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse error = ErrorResponse.generalError(500, "Internal Server Error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
