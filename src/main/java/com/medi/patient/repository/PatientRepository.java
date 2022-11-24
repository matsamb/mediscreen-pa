package com.medi.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medi.patient.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
