package com.doctorix.app.doctor.repository;

import com.doctorix.app.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {


}
