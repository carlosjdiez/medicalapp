package com.svalero.medicalapp.repository;

import com.svalero.medicalapp.domain.Clinic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Capa Repository
public interface ClinicRepository extends CrudRepository<Clinic, Long> {

    List<Clinic> findAll();
    List<Clinic> findByName(String name);
    List<Clinic> findByCity(String city);
    List<Clinic> findByNameAndCityAndPostalCode(String name, String city, String postalCode);
    List<Clinic> findByPostalCode(String postalCode);

    @Query("SELECT c FROM Clinic c WHERE c.name = :name")
    List<Clinic> findByNameJPQL(String name);

    @Query(value = "SELECT * FROM clinics WHERE name = :name", nativeQuery = true)
    List<Clinic> findByNameNative(String name);
}
