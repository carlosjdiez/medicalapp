package com.svalero.medicalapp.repository;

import com.svalero.medicalapp.domain.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findAll();
    List<Patient> findBySurnameContainingIgnoreCase(String surname);
    List<Patient> findByBirthDate(LocalDate birthDate);
    List<Patient> findByInsured(boolean insured);

    @Query("SELECT p FROM Patient p WHERE p.email = :email")
    List<Patient> findByEmailJPQL(String email);

    @Query(value = "SELECT * FROM patients WHERE email = :email", nativeQuery = true)
    List<Patient> findByEmailNative(String email);
}
