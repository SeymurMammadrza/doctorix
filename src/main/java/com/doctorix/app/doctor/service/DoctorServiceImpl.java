package com.doctorix.app.doctor.service;
import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.appointment.entity.PostAppointmentNotes;
import com.doctorix.app.appointment.entity.PostAppointmentNotesPayload;
import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.doctor.entity.DoctorPayload;
import com.doctorix.app.doctor.repository.DoctorRepository;
import com.doctorix.app.office.repository.OfficeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;
    private OfficeRepository officeRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, OfficeRepository officeRepository) {
        this.doctorRepository = doctorRepository;
        this.officeRepository = officeRepository;
    }

    @Override
    public Doctor updateById(DoctorPayload doctorPayload, long id) {
        Doctor doctor = findById(id);
        doctor.setLastName(doctorPayload.getLastName());
        doctor.setSpeciality(doctorPayload.getSpeciality());
        doctor.setOffice(officeRepository.getById(doctorPayload.getOfficeId()));
        doctor = doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public Doctor create(DoctorPayload doctorPayload) {
        log.info("Service {}", doctorPayload);
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorPayload.getFirstName());
        doctor.setLastName(doctorPayload.getLastName());
        doctor.setGender(doctorPayload.getGender());
        doctor.setSpeciality(doctorPayload.getSpeciality());
        doctor.setOffice(officeRepository.getById(doctorPayload.getOfficeId()));
        List<Appointment> emptyAppointments = new ArrayList<>();
        doctor.setAppointments(emptyAppointments);
        log.info("Service {}", doctor);
        doctor = doctorRepository.save(doctor);
        return doctor;
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
