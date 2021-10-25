package com.doctorix.app.office.entity;

import lombok.Data;

@Data
public class OfficePayload {
    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private Long zip;
    private String phoneNumber;
    private String email;
}
