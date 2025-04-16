package com.svalero.medicalapp.repository;

import com.svalero.medicalapp.domain.Specialty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {

    List<Specialty> findAll();
    List<Specialty> findByNameContainingIgnoreCase(String name);
    List<Specialty> findByActive(boolean active);
    List<Specialty> findByRequiresReferral(boolean requiresReferral);

    @Query("SELECT s FROM Specialty s WHERE s.name LIKE %:keyword%")
    List<Specialty> searchByNameJPQL(String keyword);

    @Query(value = "SELECT * FROM specialties WHERE name LIKE %:keyword%", nativeQuery = true)
    List<Specialty> searchByNameNative(String keyword);
}
