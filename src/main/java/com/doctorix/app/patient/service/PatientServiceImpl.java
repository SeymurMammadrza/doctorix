package com.doctorix.app.patient.service;

import com.doctorix.app.patient.entity.Patient;
import com.doctorix.app.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getMalePatients() {
        return patientRepository.findMalePatients();
    }
}
