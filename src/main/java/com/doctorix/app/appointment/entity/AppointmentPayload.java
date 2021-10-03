package com.doctorix.app.appointment.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentPayload {
    private AppointmentType appointmentType;
    private LocalDate date;
    private LocalTime time;
    private Long patientId;
    private Long doctorId;
    private Long officeId;
}
