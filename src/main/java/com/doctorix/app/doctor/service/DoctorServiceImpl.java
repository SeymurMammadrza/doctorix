package com.doctorix.app.doctor.service;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.appointment.entity.PostAppointmentNotes;
import com.doctorix.app.appointment.entity.PostAppointmentNotesPayload;
import com.doctorix.app.appointment.repository.AppointmentRepository;
import com.doctorix.app.appointment.service.AppointmentService;
import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.doctor.entity.DoctorPayload;
import com.doctorix.app.doctor.repository.DoctorRepository;
import com.doctorix.app.office.repository.OfficeRepository;
import com.doctorix.app.office.service.OfficeService;
import com.doctorix.app.patient.entity.Patient;
import com.doctorix.app.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;
    private OfficeRepository officeRepository;
    private OfficeService officeService;
    private AppointmentService appointmentService;
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, OfficeRepository officeRepository, OfficeService officeService, AppointmentService appointmentService, AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.officeRepository = officeRepository;
        this.officeService = officeService;
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;

    }

    @Override
    public Doctor findById(long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "doctor with this $id is not found !"));

    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;
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
        doctor.setOffice(officeService.findById(doctorPayload.getOfficeId()));
        List<Appointment> emptyAppointments = new ArrayList<>();
        doctor.setAppointments(emptyAppointments);
        log.info("Service {}", doctor);
        doctor = doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public List<Appointment> findDoctorAppointments(long doctorId) {
        Doctor doctor = findById(doctorId);
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

    @Override
    public Patient changeVaccineStatusOfPatientOfDoctor(long doctorId, long appointmentId) {
        if (appointmentRepository.existsById(appointmentId)) {
            Patient patient = findDoctorAppointments(doctorId)
                    .stream()
                    .filter(doctorAppointment -> doctorAppointment.getId()
                            .equals(appointmentId))
                    .collect(Collectors.toList())
                    .get(0)
                    .getPatient();
            boolean value = patient.isVaccinated();
            patient.isVaccinated(!value);
            patientRepository.save(patient);
            return patient;
        } else {
            throw new ObjectNotFoundException(appointmentId, "appointment with this $id is not found !");
        }
    }
}
