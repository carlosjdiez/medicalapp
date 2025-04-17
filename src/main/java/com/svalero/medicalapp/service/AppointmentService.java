package com.svalero.medicalapp.service;

import com.svalero.medicalapp.domain.Appointment;
import com.svalero.medicalapp.domain.Patient;
import com.svalero.medicalapp.domain.dto.AppointmentInDto;
import com.svalero.medicalapp.domain.dto.AppointmentOutDto;
import com.svalero.medicalapp.exception.AppointmentNotFoundException;
import com.svalero.medicalapp.exception.PatientNotFoundException;
import com.svalero.medicalapp.repository.AppointmentRepository;
import com.svalero.medicalapp.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Obtener todas las citas con filtros por fecha, motivo y estado
    public List<AppointmentOutDto> getAll(LocalDate dateTime, String reason, String status) {
        List<Appointment> appointmentList;

        if (dateTime == null && reason.isEmpty() && status.isEmpty()) {
            appointmentList = appointmentRepository.findAll();
        } else if (reason.isEmpty() && status.isEmpty()) {
            appointmentList = appointmentRepository.findByDateTime(dateTime);
        } else if (dateTime == null && status.isEmpty()) {
            appointmentList = appointmentRepository.findByReason(reason);
        } else if (dateTime == null && reason.isEmpty()) {
            appointmentList = appointmentRepository.findByStatus(status);
        } else {
            appointmentList = appointmentRepository.findByDateTimeAndReasonAndStatus(dateTime, reason, status);
        }

        return modelMapper.map(appointmentList, new TypeToken<List<AppointmentOutDto>>() {}.getType());
    }

    // Obtener una cita por ID
    public Appointment get(long id) throws AppointmentNotFoundException {
        return appointmentRepository.findById(id)
                .orElseThrow(AppointmentNotFoundException::new);
    }

    // Añadir una nueva cita y buscala por ID del paciente
    public AppointmentOutDto add(long patientId, AppointmentInDto appointmentInDto) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(PatientNotFoundException::new);

        Appointment appointment = modelMapper.map(appointmentInDto, Appointment.class);
        appointment.setPatient(patient);
        Appointment newAppointment = appointmentRepository.save(appointment);

        return modelMapper.map(newAppointment, AppointmentOutDto.class);
    }

    // Modificar una cita existente
    public AppointmentOutDto modify(long appointmentId, AppointmentInDto appointmentInDto) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(AppointmentNotFoundException::new);
        modelMapper.map(appointmentInDto, appointment);
        appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentOutDto.class);
    }

    // Eliminar una cita
    public void remove(long id) throws AppointmentNotFoundException {
        appointmentRepository.findById(id)
                .orElseThrow(AppointmentNotFoundException::new);
        appointmentRepository.deleteById(id);
    }
}
