package com.doctorix.app.patient.entity;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.entity.Office;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
/*
 Patient (one - to - many with Appointment) (FK patientID) that is why
 we have list of appointments
 */
@Data
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="gender")
    private String gender;

    @Column(name="dateOfBirth")
    private LocalDate dateOfBirth;
    @Column(name="patientStreetAddress")
    private String patientStreetAddress;
    @Column(name="patientCity")
    private String patientCity;
    @Column(name="patientState")
    private String patientState;
    @Column(name="patientZip")
    private Long patientZip;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="patientEmail")
    private String patientEmail;

    private List<Appointment> appointments;
    private List<Office> office;
}
