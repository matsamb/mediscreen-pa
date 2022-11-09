package com.medi.patient.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-configs")
public class MyConfigsProperties {

	private int patientListSizeLimit;
	
	public int getPatientListSizeLimit() {
		return patientListSizeLimit;
	}
	
	public void setPatientListSizeLimit(int size) {
		this.patientListSizeLimit = size;
	}
	
}
