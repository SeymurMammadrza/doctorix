package com.doctorix.app.entity;

import com.doctorix.app.appointment.entity.Appointment;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
/*
 Doctor (one - to - many with Appointment, many-to-many with Office) due to this we
 have list of appointments and list of offices
 */
@Data
@Table(name="doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="medicalField")
    private String medicalField;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="gender")
    private String gender;
    private List<Appointment> appointments;
    private List<Office> office;
}
