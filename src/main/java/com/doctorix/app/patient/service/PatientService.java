package com.doctorix.app.patient.service;


import com.doctorix.app.patient.entity.Patient;
import com.doctorix.app.patient.entity.PatientPayload;

import java.util.List;

public interface PatientService {
    List<Patient> getMalePatients();
    Patient create(PatientPayload patientPayload);
    Patient updateById(PatientPayload patientPayload, long id);
    Patient findById(long id);
}
