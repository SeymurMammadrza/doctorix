package com.doctorix.app.doctor.entity;

import com.doctorix.app.patient.entity.Gender;
import lombok.Data;

@Data
public class DoctorPayload {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String speciality;
    private Long officeId;
}

