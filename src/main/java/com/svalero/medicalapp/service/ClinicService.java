package com.svalero.medicalapp.service;

import com.svalero.medicalapp.domain.Clinic;
import com.svalero.medicalapp.domain.dto.ClinicInDto;
import com.svalero.medicalapp.domain.dto.ClinicOutDto;
import com.svalero.medicalapp.exception.ClinicNotFoundException;
import com.svalero.medicalapp.repository.ClinicRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Obtener todas las clínicas con filtros por nombre, ciudad y código postal
    public List<ClinicOutDto> getAll(String name, String city, String postalCode) {
        List<Clinic> clinicList;

        if (name.isEmpty() && city.isEmpty() && postalCode.isEmpty()) {
            clinicList = clinicRepository.findAll();
        } else if (city.isEmpty() && postalCode.isEmpty()) {
            clinicList = clinicRepository.findByName(name);
        } else if (name.isEmpty() && postalCode.isEmpty()) {
            clinicList = clinicRepository.findByCity(city);
        } else if (name.isEmpty() && city.isEmpty()) {
            clinicList = clinicRepository.findByPostalCode(postalCode);
        } else {
            clinicList = clinicRepository.findByNameAndCityAndPostalCode(name, city, postalCode);
        }

        return modelMapper.map(clinicList, new TypeToken<List<ClinicOutDto>>() {}.getType());
    }

    // Obtener una clínica por ID
    public Clinic get(long id) throws ClinicNotFoundException {
        return clinicRepository.findById(id)
                .orElseThrow(ClinicNotFoundException::new);
    }

    // Añadir una nueva clínica
    public ClinicOutDto add(ClinicInDto clinicInDto) {
        Clinic clinic = modelMapper.map(clinicInDto, Clinic.class);
        Clinic newClinic = clinicRepository.save(clinic);
        return modelMapper.map(newClinic, ClinicOutDto.class);
    }

    // Modificar una clínica existente
    public ClinicOutDto modify(long clinicId, ClinicInDto clinicInDto) throws ClinicNotFoundException {
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(ClinicNotFoundException::new);
        modelMapper.map(clinicInDto, clinic);
        clinicRepository.save(clinic);
        return modelMapper.map(clinic, ClinicOutDto.class);
    }

    // Eliminar una clínica
    public void remove(long id) throws ClinicNotFoundException {
        clinicRepository.findById(id)
                .orElseThrow(ClinicNotFoundException::new);
        clinicRepository.deleteById(id);
    }
}
