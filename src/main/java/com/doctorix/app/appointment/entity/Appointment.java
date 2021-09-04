package com.doctorix.app.appointment.entity;

import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.office.entity.Office;
import com.doctorix.app.patient.entity.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/*
Office (one -to - many with Appointment , many-to-many with Doctor) (FK officeId) due to this we
we have entity itself not the list of it
 */
@Data
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
    private Patient patient;

    @ManyToOne
    private Office office;

    @ManyToOne
    private Doctor doctor;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private PostAppointmentNotes postAppointmentNotes;

    @Column(name = "done")
    private Boolean isDone = false;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}
