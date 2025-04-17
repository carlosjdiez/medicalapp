package com.svalero.medicalapp.service;


import com.svalero.medicalapp.domain.Patient;
import com.svalero.medicalapp.domain.dto.PatientInDto;
import com.svalero.medicalapp.domain.dto.PatientOutDto;
import com.svalero.medicalapp.exception.PatientNotFoundException;
import com.svalero.medicalapp.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service // Capa Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Obtener todos los pacientes con filtros
    public List<PatientOutDto> getAll(String surname, String email, String phoneNumber) {
        List<Patient> patientList;

        if (surname.isEmpty() && email.isEmpty() && phoneNumber.isEmpty()) {
            patientList = patientRepository.findAll();
        } else if (email.isEmpty() && phoneNumber.isEmpty()) {
            patientList = patientRepository.findBySurname(surname);
        } else if (surname.isEmpty() && phoneNumber.isEmpty()) {
            patientList = patientRepository.findByEmail(email);
        } else if (surname.isEmpty() && email.isEmpty()) {
            patientList = patientRepository.findByPhoneNumber(phoneNumber);
        } else {
            patientList = patientRepository.findBySurnameAndEmailAndPhoneNumber(surname, email, phoneNumber);
        }

        return modelMapper.map(patientList, new TypeToken<List<PatientOutDto>>() {}.getType());
    }


    // Obtener todos los pacientes por id
    //Aqui no puedo usar un List<PartientOutDto> ya que le estoy pidiendo
    // que me devuelva un solo usuario con un ID concreto
    public Patient get(long id) throws PatientNotFoundException {
        return patientRepository.findById(id)
                .orElseThrow(PatientNotFoundException::new);
    }

    // Añadir un nuevo paciente
    public PatientOutDto add(PatientInDto patientInDto) {
        Patient patient = modelMapper.map(patientInDto, Patient.class);
        patient.setRegistrationDate(LocalDate.now());
        Patient newPatient = patientRepository.save(patient);
        return modelMapper.map(newPatient, PatientOutDto.class);
    }


    // Modificar paciente existente
    public PatientOutDto modify(long patientId, PatientInDto patientInDto) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(PatientNotFoundException::new);
        modelMapper.map(patientInDto, patient);
        patientRepository.save(patient);

        return modelMapper.map(patient, PatientOutDto.class);
    }

    // Eliminar paciente por ID
    public void remove(long id) throws PatientNotFoundException {
        patientRepository.findById(id)
                .orElseThrow(PatientNotFoundException::new);
        patientRepository.deleteById(id);
    }





    }
