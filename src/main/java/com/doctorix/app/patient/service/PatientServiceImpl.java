package com.doctorix.app.patient.service;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.patient.entity.Patient;
import com.doctorix.app.patient.entity.PatientPayload;
import com.doctorix.app.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    @Override
    public Patient create(PatientPayload patientPayload) {
        log.info("Service {}", patientPayload);
        Patient patient = new Patient();
        patient.setFirstName(patientPayload.getFirstName());
        patient.setLastName(patientPayload.getLastName());
        patient.setGender(patientPayload.getGender());
        patient.setDateOfBirth(patientPayload.getDateOfBirth());
        patient.setStreetAddress(patientPayload.getStreetAddress());
        patient.setCity(patientPayload.getCity());
        patient.setState(patientPayload.getState());
        patient.setZip(patientPayload.getZip());
        patient.setPhoneNumber(patientPayload.getPhoneNumber());
        patient.setEmail(patientPayload.getEmail());
        List<Appointment> emptyAppointments = new ArrayList<>();
        patient.setAppointments(emptyAppointments);
        log.info("Service {}", patient);
        patient = patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient updateById(PatientPayload patientPayload, long id) {
        log.info("Service {}", patientPayload);
        Patient patient = findById(id);
        patient.setFirstName(patientPayload.getFirstName());
        patient.setLastName(patientPayload.getLastName());
        patient.setGender(patientPayload.getGender());
        patient.setDateOfBirth(patientPayload.getDateOfBirth());
        patient.setStreetAddress(patientPayload.getStreetAddress());
        patient.setCity(patientPayload.getCity());
        patient.setState(patientPayload.getState());
        patient.setZip(patientPayload.getZip());
        patient.setPhoneNumber(patientPayload.getPhoneNumber());
        patient.setEmail(patientPayload.getEmail());
        log.info("Service {}", patient);
        patient = patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient findById(long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "patient with this $id is not found !"));
    }

    @Override
    public List<Appointment> listAppointments(long id ) {
       Patient patient = findById(id);
       return patient.getAppointments();
    }

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getMalePatients() {
        return patientRepository.findMalePatients();
    }
}
