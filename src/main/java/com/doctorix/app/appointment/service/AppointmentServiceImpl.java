package com.doctorix.app.appointment.service;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.appointment.entity.AppointmentPayload;
import com.doctorix.app.appointment.entity.PostAppointmentNotes;
import com.doctorix.app.appointment.entity.PostAppointmentNotesPayload;
import com.doctorix.app.appointment.repository.AppointmentRepository;
import com.doctorix.app.doctor.repository.DoctorRepository;
import com.doctorix.app.office.repository.OfficeRepository;
import com.doctorix.app.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private OfficeRepository officeRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, OfficeRepository officeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.officeRepository = officeRepository;
    }

    @Override
    public Appointment create(AppointmentPayload appointmentPayload) {
        log.info("Service {}", appointmentPayload);
        Appointment appointment = new Appointment();
        appointment.isActive(true);
        appointment.setAppointmentType(appointmentPayload.getAppointmentType());
        appointment.setAppointmentDate(appointmentPayload.getDate());
        appointment.setAppointmentTime(appointmentPayload.getTime());
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());
        appointment.setDoctor(doctorRepository.getById(appointmentPayload.getDoctorId()));
        appointment.setPatient(patientRepository.getById(appointmentPayload.getPatientId()));
        appointment.setOffice(officeRepository.getById(appointmentPayload.getOfficeId()));

        log.info("Service {}", appointment);
        appointment = appointmentRepository.save(appointment);

        return appointment;
    }

    @Override
    public Appointment updateById(AppointmentPayload appointmentPayload, long id) {
        Appointment appointment = findById(id);
        appointment.isActive(true);
        appointment.setAppointmentType(appointmentPayload.getAppointmentType());
        appointment.setAppointmentDate(appointmentPayload.getDate());
        appointment.setAppointmentTime(appointmentPayload.getTime());
        appointment.setUpdatedAt(LocalDateTime.now());
        appointment.setDoctor(doctorRepository.getById(appointmentPayload.getDoctorId()));
        appointment.setPatient(patientRepository.getById(appointmentPayload.getPatientId()));
        appointment.setOffice(officeRepository.getById(appointmentPayload.getOfficeId()));

        log.info("Service {}", appointment);
        appointment = appointmentRepository.save(appointment);

        return appointment;
    }

    @Override
    public Appointment findById(long id) {
        return appointmentRepository.findById(id).get();
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        appointmentRepository.findAll().forEach(appointments::add);
        return appointments;
    }

    @Override
    public List<Appointment> findActiveAppointments() {
        return appointmentRepository.findActiveAppointments();
    }

    @Override
    public List<Appointment> findEndedAppointments() {
        return appointmentRepository.findEndedAppointments();
    }


}
