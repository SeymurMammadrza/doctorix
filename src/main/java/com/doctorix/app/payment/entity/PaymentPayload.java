package com.doctorix.app.payment.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class PaymentPayload {

    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentType paymentType;
}
