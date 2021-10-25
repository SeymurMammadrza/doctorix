package com.doctorix.app.office.service;

import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.office.entity.Office;
import com.doctorix.app.office.entity.OfficePayload;
import com.doctorix.app.office.repository.OfficeRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OfficeServiceImpl implements OfficeService {
    @Override
    public Office findById(long id) {
        return officeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "office with this id was not found !"));
    }

    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public Office create(OfficePayload officePayload) {
        log.info("Service {}", officePayload);
        Office office = new Office();
        office.setName(officePayload.getName());
        office.setStreetAddress(officePayload.getStreetAddress());
        office.setCity(officePayload.getCity());
        office.setState(officePayload.getState());
        office.setZip(officePayload.getZip());
        office.setPhoneNumber(officePayload.getPhoneNumber());
        office.setEmail(officePayload.getEmail());
        List<Doctor> emptyDoctorList = new ArrayList<>();
        office.setDoctors(emptyDoctorList);
        officeRepository.save(office);
        return office;
    }

    @Override
    public List<Doctor> findOfficeDoctors(long officeId) {
        Office office = findById(officeId);
        return office.getDoctors();
    }
}
