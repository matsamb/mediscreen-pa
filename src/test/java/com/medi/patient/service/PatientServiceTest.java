package com.medi.patient.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medi.patient.DTO.PatientDTO;
import com.medi.patient.entity.Patient;
import com.medi.patient.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

	@Mock
	private PatientRepository patientRepository;
	
	@InjectMocks
	private PatientService patientService;
	
	@Test
	public void givenANotRegisteredPatient_whenFindPatient_thenItShouldReturnDefaultNotRegisteredPatient() {
		
		Patient patient  = new Patient();
		patient.setFamily("Lax");
		patient.setGiven("traxx");
		patient.setPatientId(2);
		
		List<Patient> patientList = List.of(patient);
		
		when(patientRepository.findAll()).thenReturn(patientList);
		
		Patient foundPatient = new Patient(patientService.findPatientByFamilyAndGiven("Max", "Plaxx"));
		
		assertThat(foundPatient.getFamily()).isEqualTo("Not_Registered");
		
	}
	
	@Test
	public void givenARegisteredPatient_whenFindPatient_thenThePatientShouldReturned() {
		
		Patient patient  = new Patient();
		patient.setFamily("Lax");
		patient.setGiven("traxx");
		patient.setPatientId(2);
		
		List<Patient> patientList = List.of(patient);
		
		when(patientRepository.findAll()).thenReturn(patientList);
		
		Patient foundPatient = new Patient(patientService.findPatientByFamilyAndGiven("Lax", "traxx"));
		
		assertThat(foundPatient.getFamily()).isEqualTo("Lax");
		
	}
	
	@Test
	public void givenARegisteredPatient_whenDeletePatient_thenRepositoryDeleteShouldBeUsedOnce() {
		
		Patient patient  = new Patient();
		patient.setFamily("Lax");
		patient.setGiven("traxx");
		patient.setPatientId(2);
		
		patientService.deletePatient(new PatientDTO(patient));	
		
		verify(patientRepository, times(1)).delete(patient);
		
	}
	
	@Test
	public void givenARegisteredPatient_whenUpdatePatient_thenRepositorySaveShouldBeUsedOnce() {
		
		Patient patient  = new Patient();
		patient.setFamily("Lax");
		patient.setGiven("traxx");
		patient.setPatientId(2);
		
		patientService.updatePatient(new PatientDTO(patient));	
		
		verify(patientRepository, times(1)).save(patient);
		
	}
	
	@Test
	public void givenANewPatient_whenSavePatient_thenRepositorySaveAndFlushShouldBeUsedOnce() {
		
		Patient patient  = new Patient();
		patient.setFamily("Lax");
		patient.setGiven("traxx");
		//patient.setPatientId(2);
		
		Patient returnedPatient  = patient;
		returnedPatient.setPatientId(1);
		
		when(patientRepository.saveAndFlush(patient)).thenReturn(returnedPatient);
		
		patientService.savePatient(new PatientDTO(patient));	
		
		verify(patientRepository, times(1)).saveAndFlush(patient);
		
	}
	
	
	
	
}
