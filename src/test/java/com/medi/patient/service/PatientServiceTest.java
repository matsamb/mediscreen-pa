package com.medi.patient.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medi.patient.entity.Patient;
import com.medi.patient.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

	@Mock
	private PatientRepository patientRepository;
	
	@InjectMocks
	private PatientService patientService;
	
	@Test
	public void test() {
		
		Patient patient  = new Patient();
		patient.setFamily("Lax");
		patient.setGiven("traxx");
		patient.setPatientId(2);
		
		List<Patient> patientList = List.of(patient);
		
		when(patientRepository.findAll()).thenReturn(patientList);
		
		Patient foundPatient = patientService.findPatientByFamilyAndGiven("Max", "Plaxx");
		
		assertThat(foundPatient.getFamily()).isEqualTo("Not_Registered");
		
	}
	
}
