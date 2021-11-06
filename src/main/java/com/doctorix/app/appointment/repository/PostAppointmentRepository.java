package com.doctorix.app.appointment.repository;

import com.doctorix.app.appointment.entity.PostAppointmentNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostAppointmentRepository extends JpaRepository<PostAppointmentNotes,Long> {

}
