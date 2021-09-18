package com.doctorix.app.doctor.service;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.appointment.entity.PostAppointmentNotesPayload;
import com.doctorix.app.doctor.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor findById(long id);
    List<Appointment> findDoctorAppointments(long doctorId);
    Appointment addNotesToAppointmentByDoctorId(long doctorId, long appointmentId, PostAppointmentNotesPayload payload);
}
