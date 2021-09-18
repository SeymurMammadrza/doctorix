package com.doctorix.app.appointment.service;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.appointment.entity.AppointmentPayload;
import com.doctorix.app.appointment.entity.PostAppointmentNotesPayload;

import java.util.List;

public interface AppointmentService {
    Appointment create(AppointmentPayload appointmentPayload);

    Appointment updateById(AppointmentPayload appointmentPayload, long id);

    Appointment findById(long id);

//    List<Appointment> findAppointmentsByPatientId(long id);
//
//    List<Appointment> findAppointmentsByDoctorId(long id);
//
//    List<Appointment> findAppointmentsByOfficeId(long id);

    List<Appointment> findAll();

    List<Appointment> findActiveAppointments();

    List<Appointment> findEndedAppointments();

    void addNotesToAppointment(PostAppointmentNotesPayload payload, long id);


}
