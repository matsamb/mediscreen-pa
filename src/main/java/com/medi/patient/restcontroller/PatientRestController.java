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
import org.springframework.util.concurrent.MonoToListenableFutureAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.medi.patient.entity.Patient;
import com.medi.patient.repository.PatientRepository;
import com.medi.patient.service.PatientService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class PatientRestController {

	@Autowired
	private PatientService patientService;

//	private PatientRepository patientRepository;

	public PatientRestController(PatientService patientService//, PatientRepository patientRepository

	) {
		this.patientService = patientService;
		//this.patientRepository = patientRepository;
	}

	// TODO dev without security
	@GetMapping("/patient")
	public ResponseEntity<Patient> findPatient(@RequestParam String family, @RequestParam String given
	/* , @RequestHeader String authentication */) {
		/*
		 * if(authentication.contentEquals("Not_Authenticated")) {
		 * log.info(given+""+family+" not authenticated"); return
		 * ResponseEntity.badRequest().build(); }else {
		 */
		if (patientService.findPatientByFamilyAndGiven(family, given).getFamily() == "Not_Registered") {
			log.info(given + "" + family + " not registered");
			return ResponseEntity.notFound().build();
		} else {
			log.info(given + "" + family + " Found");
			return ResponseEntity.ok(patientService.findPatientByFamilyAndGiven(family, given));
		}
		// }
	}

	@PostMapping("/patient/add")
	public ResponseEntity<Patient> createPatient(@RequestBody Optional<Patient> patient
	/* , @RequestHeader String authentication */) {
		/*
		 * if(authentication.contentEquals("Not_Authenticated")) {
		 * log.info(given+""+family+" not authenticated"); return
		 * ResponseEntity.badRequest().build(); }else {
		 */

		if (patient.isEmpty()) {
			log.info("No request body");
			return ResponseEntity.badRequest().build();
		} else {
			log.info("Creating new patient");
			Patient newPatient = patient.get();

			// Validator validator =
			// Validation.buildDefaultValidatorFactory().getValidator();
			// Set<ConstraintViolation<Patient>> violations =
			// validator.validate(newPatient);

			if (/* violations.size()>0 */patient.isEmpty()) {
				log.info("Bad request, constraint violations: "/* +violations */);
				return ResponseEntity.badRequest().build();
			} else {

				
				Integer savePatientId = patientService.savePatient(newPatient);
				// TODO transfert ID initialisation to user interface
				newPatient.setPatientId(savePatientId);

				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/patient")
						.buildAndExpand("?family=" + newPatient.getFamily() + "&given=" + newPatient.getGiven())
						.toUri();
				log.info("Loading new patient " + newPatient.getGiven() + " " + newPatient.getFamily() + " "
						+ newPatient.getDob());
				patientService.savePatient(newPatient);
				return ResponseEntity.created(location).body(newPatient);
			}
		}
		// }
	}

	@PutMapping("/patient")
	public ResponseEntity<Patient> updatePatient(@RequestBody Optional<Patient> patient, @RequestParam String family, @RequestParam String given
	/* , @RequestHeader String authentication */) {
		/*
		 * if(authentication.contentEquals("Not_Authenticated")) {
		 * log.info(given+""+family+" not authenticated"); return
		 * ResponseEntity.badRequest().build(); }else {
		 */

		if (patient.isEmpty()) {
			log.info("No request body");
			return ResponseEntity.badRequest().build();
		} else {
			
			Patient newPatient = patient.get();
			log.info("Updatingting new patient "+ newPatient);
			// Validator validator =
			// Validation.buildDefaultValidatorFactory().getValidator();
			// Set<ConstraintViolation<Patient>> violations =
			// validator.validate(newPatient);

			// if (/* violations.size()>0 */patient.isEmpty()) {
			// log.info("Bad request, constraint violations: "/* +violations */);
			// return ResponseEntity.badRequest().build();
			// } else {

			// TODO transfert ID initialisation to user interface
			// newPatient.setPatientId(UUID.randomUUID());
			if (patientService.findPatientByFamilyAndGiven(newPatient.getFamily(), newPatient.getGiven()).getFamily() == "Not_Registered") {
				log.info(newPatient.getGiven()+" "+ newPatient.getFamily()+" not registered");
				return ResponseEntity.notFound().build();
			} else {
				log.info("Patient "+newPatient.getGiven()+" "+ newPatient.getFamily()+" details updated");
				patientService.savePatient(newPatient);
				return ResponseEntity.ok(newPatient);
			}
			// }
		}
		// }
	}
	
	@DeleteMapping("/patient")
	public ResponseEntity<Patient> deletePatient(@RequestParam String family, @RequestParam String given
	/* , @RequestHeader String authentication */) {
		/*
		 * if(authentication.contentEquals("Not_Authenticated")) {
		 * log.info(given+""+family+" not authenticated"); return
		 * ResponseEntity.badRequest().build(); }else {
		 */
		if (patientService.findPatientByFamilyAndGiven(family, given).getFamily() == "Not_Registered") {
			log.info(given + "" + family + " not registered");
			return ResponseEntity.notFound().build();
		} else {
			log.info(given + "" + family + " Found");
			patientService.deletePatient(patientService.findPatientByFamilyAndGiven(family, given));
			return ResponseEntity.noContent().build();
		}
		// }
	}

}
