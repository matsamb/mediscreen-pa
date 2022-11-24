package com.medi.patient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.patient.DTO.PatientDTO;
import com.medi.patient.entity.Patient;
import com.medi.patient.repository.PatientRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public Integer savePatient(PatientDTO newPatient) {		
		log.info("Saving new patient: "+newPatient.getGiven()+" "+newPatient.getFamily());
		return patientRepository.saveAndFlush(new Patient(newPatient)).getPatientId();
	}
	
	public void updatePatient(PatientDTO newPatient) {		
		log.info("Updating patient: "+newPatient.getGiven()+" "+newPatient.getFamily());
		patientRepository.save(new Patient(newPatient));
	}

	public PatientDTO findPatientByFamilyAndGiven(String family, String given) {
		log.info("looking for patient: "+given+" "+family);
		Patient patientToFind = new Patient("Not_Registered");
		for(Patient p : patientRepository.findAll()) {
			
			if(p.getFamily().toLowerCase().contentEquals(family.toLowerCase())&&p.getGiven().toLowerCase().contentEquals(given.toLowerCase())) {
				log.info("Patient: "+given+" "+family+", found");
				patientToFind = p;
				break;
			}		
		}	
		return new PatientDTO(patientToFind);
	}

	public void deletePatient(PatientDTO newPatient) {
		log.info("Deleting patient: "+newPatient.getGiven()+" "+newPatient.getFamily());
		patientRepository.delete(new Patient(newPatient));		
	}
	
}
