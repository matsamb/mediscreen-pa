package com.medi.patient.DTO;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.medi.patient.entity.Patient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDTO implements Cloneable {

	private Integer patientId;
//	@Nonnull
	private String family;
//	@Nonnull
	private String given;
//	@Nonnull
	private LocalDate dob;
//	@Nonnull
	private String sex;
//	@Nonnull
	private String address;
//	@Nonnull
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
