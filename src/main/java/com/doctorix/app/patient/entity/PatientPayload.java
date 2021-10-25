package com.doctorix.app.patient.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientPayload {
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String streetAddress;
    private String city;
    private String state;
    private Long zip;
    private String phoneNumber;
    private String email;

}
