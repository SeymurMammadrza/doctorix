package com.doctorix.app.payment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "payment")
public class Payment {

    /*
    Payment (many to one relationship with Appointment)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="paymentType")
    private PaymentType paymentType;


    @Column(columnDefinition = "TIMESTAMP DEFAULT PAYMENT_CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime paymentDate;


}
