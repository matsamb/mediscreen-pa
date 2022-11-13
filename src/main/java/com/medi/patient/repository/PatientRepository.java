package com.medi.patient.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.util.concurrent.MonoToListenableFutureAdapter;

import com.medi.patient.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

//	@Query(value = "update patient (address, dob, family, given, phone, sex) values(?, ?, ?, ?, ?, ?) where patient_id = :patientId",nativeQuery = true)
//	void update(@Param("patientId") UUID patientId, Patient newPatient);
//MonoToListenableFutureAdapter<Patient> findByFamilyAndGiven(String family,String given);
}
