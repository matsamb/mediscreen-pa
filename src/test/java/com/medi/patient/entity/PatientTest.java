package com.medi.patient.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.medi.patient.DTO.PatientDTO;

public class PatientTest {

	@Test
	public void givenAPatientClone_whenModified_thenItShouldDefferFromOriginal() {
		
		Patient patient  = new Patient();
		patient.setFamily("Lax");
		patient.setGiven("traxx");
		patient.setPatientId(2);
		
		Patient copy = (Patient) patient.clone();
		
		copy.setPatientId(1);
		
		assertThat(copy).isNotEqualTo(patient);
		
	}
	
	@Test
	public void givenAPatientDTOClone_whenModified_thenItShouldDefferFromOriginal() {
		
		PatientDTO patient  = new PatientDTO();
		patient.setFamily("Lax");
		patient.setGiven("traxx");
		patient.setPatientId(2);
		
		PatientDTO copy = (PatientDTO) patient.clone();
		
		copy.setPatientId(1);
		
		assertThat(copy).isNotEqualTo(patient);
		
	}
	
	@Test
	public void givenAPatientDTO_whenPatientAllArgConstructor_thenSamePatientIdShouldBeReturned() {
		
		PatientDTO patientDto  = new PatientDTO();
		patientDto.setFamily("Lax");
		patientDto.setGiven("traxx");
		patientDto.setPatientId(2);
		
		Patient patient  = new Patient(patientDto);
						
		assertThat(patient.getPatientId()).isEqualTo(patient.getPatientId());
		
	}
	
	@Test
	public void givenAPatient_whenPatientDTOAllArgConstructor_thenSamePatientIdShouldBeReturned() {
		
		Patient patientDto  = new Patient();
		patientDto.setFamily("Lax");
		patientDto.setGiven("traxx");
		patientDto.setPatientId(2);
		
		PatientDTO patient  = new PatientDTO(patientDto);
						
		assertThat(patient.getPatientId()).isEqualTo(patient.getPatientId());
		
	}
	
}
