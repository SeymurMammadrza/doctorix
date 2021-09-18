package com.doctorix.app.appointment.repository;

import com.doctorix.app.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query("select a from appointments a where a.isActive=:true")//entity-based query
    List<Appointment> findActiveAppointments();

    @Query("select a from appointments a where a.isDone=:true")//entity-based query
    List<Appointment> findEndedAppointments();

    void deleteById(long id);


}
