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
import org.springframework.http.HttpStatus;
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

import com.medi.patient.DTO.PatientDTO;
import com.medi.patient.entity.Patient;
import com.medi.patient.repository.PatientRepository;
import com.medi.patient.service.PatientService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class PatientRestController {

	@Autowired
	private PatientService patientService;

	public PatientRestController(PatientService patientService// , PatientRepository patientRepository

	) {
		this.patientService = patientService;
		// this.patientRepository = patientRepository;
	}

	// TODO dev without security
	@GetMapping("/patient")
	public ResponseEntity<PatientDTO> findPatient(@RequestParam String family, @RequestParam String given) {

		if (patientService.findPatientByFamilyAndGiven(family, given).getFamily() == "Not_Registered") {
			log.info(given + "" + family + " not registered");
			return ResponseEntity.noContent().build();
		} else {
			log.info(given + "" + family + " Found");
			
			return ResponseEntity.ok(new PatientDTO(patientService.findPatientByFamilyAndGiven(family, given)));
		}
	}

	@PostMapping("/patient/add")
	public ResponseEntity<PatientDTO> createPatient(@RequestBody Optional<PatientDTO> patientDTO) {
		if (patientDTO.isEmpty()) {
			log.info("No request body");
			return ResponseEntity.badRequest().build();
		} else {
			log.info("Creating new patient");
			Patient newPatient = new Patient(patientDTO.get());
			log.info("Creating new patient " + newPatient);

			if (patientService.findPatientByFamilyAndGiven(newPatient.getFamily(), newPatient.getGiven())
					.getFamily() == "Not_Registered") {

				log.info("Creating new patient " + newPatient);
				Integer savePatientId = patientService.savePatient(newPatient);
				// TODO transfert ID initialisation to user interface
				newPatient.setPatientId(savePatientId);

				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/patient")
						.buildAndExpand("?family=" + newPatient.getFamily() + "&given=" + newPatient.getGiven())
						.toUri();
				log.info("Loading new patient " + newPatient.getGiven() + " " + newPatient.getFamily() + " "
						+ newPatient.getDob());

				return ResponseEntity.created(location).body(new PatientDTO( newPatient));

			} else {
				log.info(newPatient.getGiven() + " " + newPatient.getFamily() + " all ready registered");
				return ResponseEntity.noContent().build();

			}
		}
	}

	@PutMapping("/patient")
	public ResponseEntity<PatientDTO> updatePatient(@RequestBody Optional<PatientDTO> patientDTO, @RequestParam String family,
			@RequestParam String given) {

		if(patientDTO.isPresent()) {
		Patient newPatient = new Patient(patientDTO.get());
		log.info("Updatingting new patient " + newPatient);

		if (patientService.findPatientByFamilyAndGiven(newPatient.getFamily(), newPatient.getGiven())
				.getFamily() == "Not_Registered") {
			log.info(newPatient.getGiven() + " " + newPatient.getFamily() + " not registered");
			return ResponseEntity.noContent().build();
		} else {
			log.info("Patient " + newPatient.getGiven() + " " + newPatient.getFamily() + " details updated");
			patientService.savePatient(newPatient);
			return ResponseEntity.ok(new PatientDTO(newPatient));
		}
		}else {
			log.info("NoRequestBody ");
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/patient")
	public ResponseEntity<PatientDTO> deletePatient(@RequestParam String family, @RequestParam String given) {

		log.info(given + "" + family + " Found");
		patientService.deletePatient(patientService.findPatientByFamilyAndGiven(family, given));
		return ResponseEntity.noContent().build();

	}
}