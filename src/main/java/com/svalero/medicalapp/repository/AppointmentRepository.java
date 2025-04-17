package com.svalero.medicalapp.repository;

import com.svalero.medicalapp.domain.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository //Capa Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    List<Appointment> findAll();
    List<Appointment> findByDateTime(LocalDate dateTime);
    List<Appointment> findByReason(String reason);
    List<Appointment> findByStatus(String status);
    List<Appointment> findByDateTimeAndReasonAndStatus(LocalDate dateTime, String reason, String status);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId")
    List<Appointment> findByPatientIdJPQL(Long patientId);

    @Query(value = "SELECT * FROM appointments WHERE patient_id = :patientId", nativeQuery = true)
    List<Appointment> findByPatientIdNative(Long patientId);
}
