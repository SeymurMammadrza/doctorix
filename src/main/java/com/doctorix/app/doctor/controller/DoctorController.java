package com.doctorix.app.doctor.controller;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.appointment.entity.PostAppointmentNotesPayload;
import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.doctor.entity.DoctorPayload;
import com.doctorix.app.doctor.repository.DoctorRepository;
import com.doctorix.app.doctor.service.DoctorService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor")

public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorService doctorService, DoctorRepository doctorRepository) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
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

    @PutMapping("/update/{id}")
    ResponseEntity<Doctor> updateDoctor(@PathVariable (value = "id") Long doctorId, @RequestBody DoctorPayload doctorPayload){
        System.out.println("Controller " + doctorPayload);
        return new ResponseEntity<>(doctorService.updateById(doctorPayload, doctorId), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    ResponseEntity<Doctor> createDoctor(@RequestBody DoctorPayload doctorPayload){
        System.out.println("Controller " + doctorPayload);
        return new ResponseEntity<>(doctorService.create(doctorPayload), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteDoctor(@PathVariable (value = "id") Long doctorId) throws NotFoundException{
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Appointment not found for this id :: " + doctorId));
        doctorRepository.delete(doctor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted doctor with this id " + doctorId, Boolean.TRUE);
        return response;
    }
}
