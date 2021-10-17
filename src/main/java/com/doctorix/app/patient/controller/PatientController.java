package com.doctorix.app.patient.controller;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.doctor.entity.DoctorPayload;
import com.doctorix.app.patient.entity.Patient;
import com.doctorix.app.patient.entity.PatientPayload;
import com.doctorix.app.patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/list/male")
    ResponseEntity<List<Patient>> getMalePatients() {
        return new ResponseEntity<>(patientService.getMalePatients(), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Patient> createPatient(@RequestBody PatientPayload patientPayload){
        System.out.println("Controller " + patientPayload);
        return new ResponseEntity<>(patientService.create(patientPayload), HttpStatus.CREATED);
    }
}
