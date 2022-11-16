package com.medi.patient.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Patient implements Cloneable{

	//"family=TestNone&given=Test&dob=1966-12-31&sex=F&address=1 Brookside St&phone=100-222-3333"
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	public Patient(String string) {
		this.family = string;
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
