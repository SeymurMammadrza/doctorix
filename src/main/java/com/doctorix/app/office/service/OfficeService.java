package com.doctorix.app.office.service;

import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.office.entity.Office;
import com.doctorix.app.office.entity.OfficePayload;

import java.util.List;

public interface OfficeService {
    Office create (OfficePayload officePayload);
    Office findById(long id);
    List<Doctor> findOfficeDoctors(long officeId);
}
