package com.svalero.medicalapp.repository;

import com.svalero.medicalapp.domain.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository //Capa Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findAll();
    List<Patient> findBySurnameContainingIgnoreCase(String surname);
    List<Patient> findByInsured(boolean insured);
    List<Patient> findBySurname (String surname);
    List<Patient> findBySurnameAndEmailAndPhoneNumber (String surname, String email, String phoneNumber);
    List<Patient> findByEmail (String email);
    List<Patient> findByPhoneNumber (String phoneNumber);

    @Query("SELECT p FROM Patient p WHERE p.email = :email")
    List<Patient> findByEmailJPQL(String email);

    @Query(value = "SELECT * FROM patients WHERE email = :email", nativeQuery = true)
    List<Patient> findByEmailNative(String email);
}
