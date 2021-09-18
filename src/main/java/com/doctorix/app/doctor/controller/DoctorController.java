package com.doctorix.app.doctor.controller;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.appointment.entity.PostAppointmentNotesPayload;
import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.doctor.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")

public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Doctor> getDoctorById(@PathVariable (value = "id") Long id){
        return new ResponseEntity<>(doctorService.findById(id), HttpStatus.OK);

    }

    @GetMapping("/{id}/appointment/list")
    ResponseEntity<List<Appointment>> getAllDoctorAppointments(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(doctorService.findDoctorAppointments(id), HttpStatus.OK);
    }

    @PostMapping("/{doctorId}/appointment/{appointmentId}/setNotes")
    ResponseEntity<Appointment> addNotesToAppointment(@PathVariable(value = "doctorId") Long doctorId,
                                                      @PathVariable(value = "appointmentId") Long appointmentId,
                                                      @RequestBody PostAppointmentNotesPayload payload){
        return new ResponseEntity<>(doctorService.addNotesToAppointmentByDoctorId(doctorId, appointmentId, payload), HttpStatus.CREATED);

    }

}
