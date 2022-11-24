package com.medi.patient.DTO;

import java.time.LocalDate;

import com.medi.patient.entity.Patient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDTO implements Cloneable {

	private Integer patientId;
	private String family;
	private String given;
	private LocalDate dob;
	private String sex;
	private String address;
	private String phone;

	public PatientDTO(Patient patient) {
		this.patientId = patient.getPatientId();
		this.family = patient.getFamily();
		this.given = patient.getGiven();
		this.dob = patient.getDob();
		this.sex = patient.getSex();
		this.address = patient.getAddress();
		this.phone = patient.getPhone();
	}

	public Object clone() {
		PatientDTO copy = null;
		try {
			copy = (PatientDTO) super.clone();
		} catch (CloneNotSupportedException c) {
			c.printStackTrace();
		}
		return copy;
	}

}
