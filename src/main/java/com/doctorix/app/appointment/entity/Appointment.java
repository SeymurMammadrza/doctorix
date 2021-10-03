package com.doctorix.app.appointment.entity;

import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.patient.entity.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
Office (one -to - many with Appointment , many-to-many with Doctor) (FK officeId) due to this we
 have entity itself not the list of it
 */
@Getter
@Setter
@Entity(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "appointment_type")
    private AppointmentType appointmentType;

    @Column(name = "appointment_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private LocalDate appointmentDate;

    @Column(name = "appointment_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime appointmentTime;

    @ManyToOne
    @JsonBackReference
    private Patient patient;

    @ManyToOne
    @JsonBackReference
    private Doctor doctor;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    @Nullable
    private PostAppointmentNotes postAppointmentNotes;

    @Column(name = "is_done")
    @Accessors(fluent = true)
    private Boolean isDone = false;

    @Column(name = "is_active")
    @Accessors(fluent = true)
    private Boolean isActive = false;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
