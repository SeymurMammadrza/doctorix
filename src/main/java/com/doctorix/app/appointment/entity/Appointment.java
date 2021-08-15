package com.doctorix.app.appointment.entity;

import com.doctorix.app.entity.Doctor;
import com.doctorix.app.entity.Office;
import com.doctorix.app.patient.entity.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/*
Office (one -to - many with Appointment , many-to-many with Doctor) (FK officeId) due to this we
we have entity itself not the list of it
 */
@Data
@Table(name="appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="appointment_type")
    private AppointmentType appointmentType;

    @Column(name = "appointment_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate appointmentDate;

    @Column(name = "appointment_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm:ss")
    private LocalTime appointmentTime;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Office office;

    @ManyToOne
    private Doctor doctor;

}
