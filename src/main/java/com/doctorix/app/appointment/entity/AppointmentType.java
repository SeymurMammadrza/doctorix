package com.doctorix.app.appointment.entity;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name="AppointmentType")
public enum AppointmentType {

    Consultation,

    Vaccination,
    Surgery,
    Test,
    Routine,
}
