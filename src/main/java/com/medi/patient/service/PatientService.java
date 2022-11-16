package com.medi.patient.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.patient.entity.Patient;
import com.medi.patient.repository.PatientRepository;
//import com.netflix.discovery.converters.Auto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public Integer savePatient(Patient newPatient) {		
		log.info("Saving new patient: "+newPatient.getGiven()+" "+newPatient.getFamily());
		return patientRepository.saveAndFlush(newPatient).getPatientId();
	}
	
	public void updatePatient(Patient newPatient) {		
		log.info("Updating patient: "+newPatient.getGiven()+" "+newPatient.getFamily());
		patientRepository.save(newPatient);
	}

	public Patient findPatientByFamilyAndGiven(String family, String given) {
		log.info("looking for patient: "+given+" "+family);
		Patient patientToFind = new Patient("Not_Registered");
		for(Patient p : patientRepository.findAll()) {
			
			if(p.getFamily().contentEquals(family)&&p.getGiven().contentEquals(given)) {
				log.info("Patient: "+given+" "+family+", found");
				patientToFind = p;
				break;
			}		
		}	
		return patientToFind;
	}

	public void deletePatient(Patient newPatient) {
		log.info("Deleting patient: "+newPatient.getGiven()+" "+newPatient.getFamily());
		patientRepository.delete(newPatient);		
	}
	
}
