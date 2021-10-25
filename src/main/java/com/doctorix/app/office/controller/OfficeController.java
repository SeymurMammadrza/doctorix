package com.doctorix.app.office.controller;

import com.doctorix.app.doctor.entity.Doctor;
import com.doctorix.app.office.entity.Office;
import com.doctorix.app.office.entity.OfficePayload;
import com.doctorix.app.office.repository.OfficeRepository;
import com.doctorix.app.office.service.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/office")
public class OfficeController {
    private final OfficeRepository officeRepository;
    private final OfficeService officeService;

    public OfficeController(OfficeRepository officeRepository, OfficeService officeService) {
        this.officeRepository = officeRepository;
        this.officeService = officeService;
    }

    @PostMapping("/create")
    ResponseEntity<Office> createOffice(@RequestBody OfficePayload officePayload) {
        System.out.println("Controller " + officePayload);
        return new ResponseEntity<>(officeService.create(officePayload), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/doctor/list")
    ResponseEntity<List<Doctor>> getAllOfficeDoctors(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(officeService.findOfficeDoctors(id), HttpStatus.OK);
    }
}
