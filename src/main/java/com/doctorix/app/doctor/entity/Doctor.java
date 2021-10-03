package com.doctorix.app.doctor.entity;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.office.entity.Office;
import com.doctorix.app.patient.entity.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/*
 Doctor (one - to - many with Appointment, many-to-many with Office) due to this we
 have list of appointments and list of offices
 */
@Getter
@Setter
@Entity(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="gender")
    private Gender gender;

    @Column(name="speciality")
    private String speciality;

    @Column(name = "appointments")
    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private Office office;
}
