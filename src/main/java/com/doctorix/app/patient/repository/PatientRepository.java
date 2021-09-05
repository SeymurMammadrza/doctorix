package com.doctorix.app.patient.repository;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("select p from patients p where p.gender=:MALE")//entity-based query
    List<Patient> findMalePatients();
}
