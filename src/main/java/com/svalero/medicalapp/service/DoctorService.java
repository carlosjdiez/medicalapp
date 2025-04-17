package com.svalero.medicalapp.service;

import com.svalero.medicalapp.domain.Doctor;
import com.svalero.medicalapp.domain.dto.DoctorInDto;
import com.svalero.medicalapp.domain.dto.DoctorOutDto;
import com.svalero.medicalapp.exception.DoctorNotFoundException;
import com.svalero.medicalapp.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Obtener todos los doctores con filtros
    public List<DoctorOutDto> getAll(String surname, String specialty, String email) {
        List<Doctor> doctorList;

        if (surname.isEmpty() && specialty.isEmpty() && email.isEmpty()) {
            doctorList = doctorRepository.findAll();
        } else if (specialty.isEmpty() && email.isEmpty()) {
            doctorList = doctorRepository.findBySurname(surname);
        } else if (surname.isEmpty() && email.isEmpty()) {
            doctorList = doctorRepository.findBySpecialty(specialty);
        } else if (surname.isEmpty() && specialty.isEmpty()) {
            doctorList = doctorRepository.findByEmail(email);
        } else {
            doctorList = doctorRepository.findBySurnameAndSpecialtyAndEmail(surname, specialty, email);
        }

        return modelMapper.map(doctorList, new TypeToken<List<DoctorOutDto>>() {}.getType());
    }

    // Obtener un doctor por ID
    public Doctor get(long id) throws DoctorNotFoundException {
        return doctorRepository.findById(id)
                .orElseThrow(DoctorNotFoundException::new);
    }

    // Añadir un nuevo doctor
    public DoctorOutDto add(DoctorInDto doctorInDto) {
        Doctor doctor = modelMapper.map(doctorInDto, Doctor.class);
        Doctor newDoctor = doctorRepository.save(doctor);
        return modelMapper.map(newDoctor, DoctorOutDto.class);
    }

    // Modificar un doctor
    public DoctorOutDto modify(long doctorId, DoctorInDto doctorInDto) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(DoctorNotFoundException::new);
        modelMapper.map(doctorInDto, doctor);
        doctorRepository.save(doctor);
        return modelMapper.map(doctor, DoctorOutDto.class);
    }

    // Eliminar un doctor por ID
    public void remove(long id) throws DoctorNotFoundException {
        doctorRepository.findById(id)
                .orElseThrow(DoctorNotFoundException::new);
        doctorRepository.deleteById(id);
    }
}
