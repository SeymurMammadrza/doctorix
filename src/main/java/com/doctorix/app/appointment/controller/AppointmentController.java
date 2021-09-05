package com.doctorix.app.appointment.controller;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.appointment.entity.AppointmentPayload;
import com.doctorix.app.appointment.repository.AppointmentRepository;
import com.doctorix.app.appointment.service.AppointmentService;
import javassist.NotFoundException;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;


    public AppointmentController(AppointmentService appointmentService, AppointmentRepository appointmentRepository) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/list")
    ResponseEntity<List<Appointment>> getAll() {
        return new ResponseEntity<>(appointmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/active/list")
    ResponseEntity<List<Appointment>> getActiveAppointments() {
        return new ResponseEntity<>(appointmentService.findActiveAppointments(), HttpStatus.OK);
    }

    @GetMapping("/done/list")
    ResponseEntity<List<Appointment>> getEndedAppointments() {
        return new ResponseEntity<>(appointmentService.findEndedAppointments(), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentPayload appointmentPayload) {
        System.out.println("Controller " + appointmentPayload);
        return new ResponseEntity<>(appointmentService.create(appointmentPayload), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteAppointment(@PathVariable(value = "id") Long appointmentId)
            throws NotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found for this id :: " + appointmentId));

        appointmentRepository.delete(appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

