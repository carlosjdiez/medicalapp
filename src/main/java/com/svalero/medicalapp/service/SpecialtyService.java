package com.svalero.medicalapp.service;

import com.svalero.medicalapp.domain.Specialty;
import com.svalero.medicalapp.domain.dto.SpecialtyInDto;
import com.svalero.medicalapp.domain.dto.SpecialtyOutDto;
import com.svalero.medicalapp.exception.SpecialtyNotFoundException;
import com.svalero.medicalapp.repository.SpecialtyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Obtener todas las especialidades con filtros
    public List<SpecialtyOutDto> getAll(String name, Boolean requiresReferral, Boolean active) {
        List<Specialty> specialtyList;

        if (name.isEmpty() && requiresReferral == null && active == null) {
            specialtyList = specialtyRepository.findAll();
        } else if (requiresReferral == null && active == null) {
            specialtyList = specialtyRepository.findByName(name);
        } else if (name.isEmpty() && active == null) {
            specialtyList = specialtyRepository.findByRequiresReferral(requiresReferral);
        } else if (name.isEmpty() && requiresReferral == null) {
            specialtyList = specialtyRepository.findByActive(active);
        } else {
            specialtyList = specialtyRepository.findByNameAndRequiresReferralAndActive(name, requiresReferral, active);
        }

        return modelMapper.map(specialtyList, new TypeToken<List<SpecialtyOutDto>>() {}.getType());
    }

    // Obtener especialidad por ID
    public Specialty get(long id) throws SpecialtyNotFoundException {
        return specialtyRepository.findById(id)
                .orElseThrow(SpecialtyNotFoundException::new);
    }

    // Añadir nueva especialidad
    public SpecialtyOutDto add(SpecialtyInDto specialtyInDto) {
        Specialty specialty = modelMapper.map(specialtyInDto, Specialty.class);
        Specialty newSpecialty = specialtyRepository.save(specialty);
        return modelMapper.map(newSpecialty, SpecialtyOutDto.class);
    }

    // Modificar una especialidad
    public SpecialtyOutDto modify(long specialtyId, SpecialtyInDto specialtyInDto) throws SpecialtyNotFoundException {
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(SpecialtyNotFoundException::new);
        modelMapper.map(specialtyInDto, specialty);
        specialtyRepository.save(specialty);
        return modelMapper.map(specialty, SpecialtyOutDto.class);
    }

    // Eliminar especialidad por ID
    public void remove(long id) throws SpecialtyNotFoundException {
        specialtyRepository.findById(id)
                .orElseThrow(SpecialtyNotFoundException::new);
        specialtyRepository.deleteById(id);
    }
}
