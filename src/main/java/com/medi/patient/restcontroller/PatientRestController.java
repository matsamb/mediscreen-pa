package com.medi.patient.restcontroller;

import java.net.URI;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.medi.patient.entity.Patient;
import com.medi.patient.service.PatientService;

import lombok.extern.log4j.Log4j2;

@RestController
//@RequestMapping("/patient")
@Log4j2
public class PatientRestController {

	@Autowired
	private PatientService patientService;

	public PatientRestController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping("/patient")
	public ResponseEntity<Patient> findPatient(@RequestParam String family, @RequestParam String given){
		
		if(patientService.findPatientByFamilyAndGiven(family,given).getFamily() == "Not_Registered") {
			log.info(given+""+family+" not registered");
			return ResponseEntity.notFound().build();
		}else {
			log.info(given+""+family+" Found");
			return ResponseEntity.ok(patientService.findPatientByFamilyAndGiven(family,given));
		}		
	}
	
	@PostMapping("/patient/add")
	public ResponseEntity<Patient> createPatient(@RequestBody Optional<Patient> patient) {

		if (patient.isEmpty()) {
			log.info("No request body");
			return ResponseEntity.badRequest().build();
		} else {
			log.info("Creating new patient");
			Patient newPatient = patient.get();
			
			//Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
			//Set<ConstraintViolation<Patient>> violations = validator.validate(newPatient);
			
			if(/*violations.size()>0*/patient.isEmpty()) {
				log.info("Bad request, constraint violations: "/*+violations*/);
				return ResponseEntity.badRequest().build();
			}else {
				
				//TODO transfert ID initialisation to user interface
				newPatient.setPatientId(UUID.randomUUID());
				
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/patient")
						.buildAndExpand("?family="+newPatient.getFamily()+"&given="+newPatient.getGiven()).toUri();			
				log.info("Loading new patient "+newPatient.getGiven()+" "+newPatient.getFamily()+" "+newPatient.getDob());
				patientService.savePatient(newPatient);
				return ResponseEntity.created(location).body(newPatient);
			}
		}
	}

}
