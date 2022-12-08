package com.medi.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.medi.patient.configuration.MedConfigs;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
//@EnableDiscoveryClient   
public class PatientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info(medConfigs.getAngularurl());
		
	}
	
	@Autowired
	private MedConfigs medConfigs;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				registry
					.addMapping("/patient")
					.allowedOrigins(medConfigs.getAngularurl()/*"localhost:4200""${medconfigs.angularurl}"*/);
			}
		};
	}

}
