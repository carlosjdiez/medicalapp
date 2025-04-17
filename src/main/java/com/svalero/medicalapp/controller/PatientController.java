package com.svalero.medicalapp.controller;

import com.svalero.medicalapp.domain.Patient;
import com.svalero.medicalapp.domain.dto.PatientInDto;
import com.svalero.medicalapp.domain.dto.PatientOutDto;
import com.svalero.medicalapp.exception.PatientNotFoundException;
import com.svalero.medicalapp.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PatientController {


    @Autowired
    private PatientService patientService;
    private final Logger logger = LoggerFactory.getLogger(AppointmentController.class);


    @PostMapping("/patients")
    public ResponseEntity<PatientOutDto> addPatient(@Valid @RequestBody PatientInDto patientInDto) {
        logger.info("BEGIN addPatient");
        PatientOutDto newPatient = patientService.add(patientInDto);
        logger.info("END addPatient");
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }


    @ExceptionHandler // Este es el 404 NOT FOUND
    public ResponseEntity<Void> handleUserNotFoundException(PatientNotFoundException exception) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
