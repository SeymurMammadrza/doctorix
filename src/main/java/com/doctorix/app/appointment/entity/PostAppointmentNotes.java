package com.doctorix.app.appointment.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "post_appointment_notes")
public class PostAppointmentNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "notes")
    private String notes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
