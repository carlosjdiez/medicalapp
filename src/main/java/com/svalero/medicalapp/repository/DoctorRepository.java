package com.svalero.medicalapp.repository;

import com.svalero.medicalapp.domain.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Capa Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    List<Doctor> findAll();
    List<Doctor> findBySurname(String surname);
    List<Doctor> findBySpecialty(String specialty);
    List<Doctor> findByEmail(String email);
    List<Doctor> findBySurnameAndSpecialtyAndEmail(String surname, String specialty, String email);


    @Query("SELECT d FROM Doctor d WHERE d.email = :email")
    Doctor findByEmailJPQL(String email);

    @Query(value = "SELECT * FROM doctors WHERE email = :email", nativeQuery = true)
    Doctor findByEmailNative(String email);
}
