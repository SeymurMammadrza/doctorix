package com.doctorix.app.doctor.service;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.appointment.entity.PostAppointmentNotes;
import com.doctorix.app.appointment.entity.PostAppointmentNotesPayload;
import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.doctor.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor findById(long id) {
        return doctorRepository.findById(id).get();
    }

    @Override
    public List<Appointment> findDoctorAppointments(long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).get();
        return doctor.getAppointments();
    }

    @Override
    public Appointment addNotesToAppointmentByDoctorId(long doctorId, long appointmentId, PostAppointmentNotesPayload payload) {
        List<Appointment> doctorAppointments = findDoctorAppointments(doctorId);
        Appointment appointment = doctorAppointments.stream().filter(doctorAppointment -> doctorAppointment.getId()
                .equals(appointmentId)).collect(Collectors.toList()).get(0);

        PostAppointmentNotes postAppointmentNotes = new PostAppointmentNotes();
        postAppointmentNotes.setNotes(payload.getNotes());
        appointment.setPostAppointmentNotes(postAppointmentNotes);
        return appointment;
    }


}
