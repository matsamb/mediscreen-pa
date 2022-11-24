package com.medi.patient.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.medi.patient.DTO.PatientDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Patient implements Cloneable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientId;
	private String family;
	private String given;
	private LocalDate dob;
	private String sex;
	private String address;
	private String phone;
	
	public Patient(String string) {
		this.family = string;
	}
	
	public Patient(PatientDTO patient) {
		this.patientId = patient.getPatientId();
		this.family = patient.getFamily();
		this.given = patient.getGiven();
		this.dob = patient.getDob();
		this.sex = patient.getSex();
		this.address = patient.getAddress();
		this.phone = patient.getPhone();
	}
	
	public Object clone() {
		Patient copy = null;
		try {
			copy = (Patient)super.clone();
		}catch(CloneNotSupportedException c){
			c.printStackTrace();
		}
		return copy;
	}
	
}
