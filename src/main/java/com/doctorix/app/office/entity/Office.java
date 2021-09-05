package com.doctorix.app.office.entity;

import com.doctorix.app.appointment.entity.Appointment;
import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.patient.entity.Patient;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
Office (one -to - many with Appointment , many-to-many with Doctor) (FK officeId)
due to this we have list of the appointments
 */
@Data
@Entity(name = "offices")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private Long zip;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "office")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "office")
    private List<Doctor> doctors = new ArrayList<>();
}
