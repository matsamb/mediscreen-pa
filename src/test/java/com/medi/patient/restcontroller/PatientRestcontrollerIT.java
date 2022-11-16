package com.medi.patient.restcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.medi.patient.entity.Patient;
import com.medi.patient.service.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientRestcontrollerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PatientService patientService;
	
	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext) {
		
		mockMvc = MockMvcBuilders
					.webAppContextSetup(webApplicationContext)
					//.apply(springSecurity())
					.build();
		
	}
	
	@Test
	public void test() throws Exception{
		
		when(patientService.savePatient(new Patient())).thenReturn(1);
		
		mockMvc.perform(post("/patient/add")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
	}
	
}
