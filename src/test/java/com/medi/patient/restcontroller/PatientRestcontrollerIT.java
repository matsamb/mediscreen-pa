package com.medi.patient.restcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.http.HttpRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
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
					.build();
		
	}
	
	@Test
	public void givenANotAuthenticatedUser_whenDeletePatientForNotARegisteredOne_thenItShouldReturnStatusBadRequest() throws Exception{
		
		Patient patient  = new Patient();
		patient.setFamily("Not_Registered");
		//patient.setGiven("Test");
		//patient.setPatientId(1);
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		when(patientService.savePatient(new Patient())).thenReturn(1);
		
		mockMvc.perform(delete("/patient")	
				//.header("Authentication", "bearer")
				.param("family", "TestNone")
				.param("given", "Test")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void givenAnAuthenticatedUser_whenDeletePatientForNotARegisteredOne_thenItShouldReturnNoContent() throws Exception{
		
		Patient patient  = new Patient("Not_Registered");
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		//when(patientService.savePatient(new Patient())).thenReturn(1);
		
		mockMvc.perform(delete("/patient")	
				.header("Authentication", "bearer")
				.param("family", "TestNone")
				.param("given", "Test")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"patientId\":1,\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void givenAnAuthenticatedUser_whenDeletePatientForARegisteredOne_thenItShouldReturnStatusNoContent() throws Exception{
		
		Patient patient  = new Patient("TestNone");
		patient.setGiven("Test");
		patient.setPatientId(1);		
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		//when(patientService.savePatient(new Patient())).thenReturn(1);
		
		mockMvc.perform(delete("/patient")	
				.header("Authentication", "bearer")
				.param("family", "TestNone")
				.param("given", "Test")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"patientId\":1,\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void givenANotAuthenticatedUser_whenPutPatientForNotARegisteredOne_thenItShouldReturnStatusBadRequest() throws Exception{
		
		Patient patient  = new Patient();
		patient.setFamily("Not_Registered");
		//patient.setGiven("Test");
		//patient.setPatientId(1);
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		when(patientService.savePatient(new Patient())).thenReturn(1);
		
		mockMvc.perform(put("/patient")	
				//.header("Authentication", "bearer")
				.param("family", "TestNone")
				.param("given", "Test")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void givenAnAuthenticatedUser_whenPutPatientForNotARegisteredOne_thenItShouldReturnNoContent() throws Exception{
		
		Patient patient  = new Patient("Not_Registered");
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		//when(patientService.savePatient(new Patient())).thenReturn(1);
		
		mockMvc.perform(put("/patient")	
				.header("Authentication", "bearer")
				.param("family", "TestNone")
				.param("given", "Test")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"patientId\":1,\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void givenAnAuthenticatedUser_whenPutPatientForARegisteredOne_thenItShouldReturnStatusOk() throws Exception{
		
		Patient patient  = new Patient("TestNone");
		patient.setGiven("Test");
		patient.setPatientId(1);		
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		//when(patientService.savePatient(new Patient())).thenReturn(1);
		
		mockMvc.perform(put("/patient")	
				.header("Authentication", "bearer")
				.param("family", "TestNone")
				.param("given", "Test")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"patientId\":1,\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void givenANotAuthenticatedUser_whenPostPatientAdd_thenItShouldReturnStatusBadRequest() throws Exception{
		
		Patient patient  = new Patient();
		patient.setFamily("Not_Registered");
		//patient.setGiven("Test");
		//patient.setPatientId(1);
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		when(patientService.savePatient(new Patient())).thenReturn(1);
		
		mockMvc.perform(post("/patient/add")	
				//.header("Authentication", "bearer")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void givenAnAuthenticatedUser_whenPostPatientAdd_thenItShouldReturnStatusOk() throws Exception{
		
		Patient patient  = new Patient("Not_Registered");
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		//when(patientService.savePatient(new Patient())).thenReturn(1);
		
		mockMvc.perform(post("/patient/add")	
				.header("Authentication", "bearer")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void givenANotAuthenticatedUser_whenGetPatientAdd_thenItShouldReturnStatusBadRequest() throws Exception{
		
		Patient patient  = new Patient();
		patient.setFamily("Not_Registered");
		//patient.setGiven("Test");
		//patient.setPatientId(1);
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		
		mockMvc.perform(get("/patient")	
				//.header("Authentication", "bearer")
				.param("family", "TestNone")
				.param("given", "Test")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void givenAnAuthenticatedUser_whenGetPatientForANotRegisteredOne_thenItShouldReturnStatusNoContent() throws Exception{
		
		Patient patient  = new Patient("Not_Registered");
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		
		mockMvc.perform(get("/patient")	
				.header("Authentication", "bearer")
				.param("family", "TestNone")
				.param("given", "Test")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void givenAnAuthenticatedUser_whenGetPatientForARegisteredOne_thenItShouldReturnStatusNoContent() throws Exception{
		
		Patient patient  = new Patient("TestNone");
		patient.setGiven("Test");
		patient.setPatientId(1);
		
		when(patientService.findPatientByFamilyAndGiven("TestNone","Test")).thenReturn(patient);
		
		mockMvc.perform(get("/patient")	
				.header("Authentication", "bearer")
				.param("family", "TestNone")
				.param("given", "Test")
				.contentType(MediaType.APPLICATION_JSON)					
					
					.content("{\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}
	
}
