package com.exportimport.restapi.test.controller.customer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.exportimport.restapi.controller.CustomerController;
import com.exportimport.restapi.model.Customer;
import com.exportimport.restapi.service.CustomerService;
import com.exportimport.restapi.util.CustomerInstanceProvider;
import com.exportimport.restapi.util.CustomerValidation;
import com.exportimport.restapi.util.JSONConvertion;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private CustomerValidation customerValidation;

	@InjectMocks
	private CustomerController customerController;

	private Customer customer;

	@Before
	public void setup() {
		customer = CustomerInstanceProvider.getCustomer();
	}

	@Test
	public void addCustomer() throws Exception {

		when(customerService.save(this.customer)).thenReturn(this.customer);

		String jsonData = JSONConvertion.convertObjectToJsonBytes(this.customer);

		mockMvc.perform(post("/api/customer").content(jsonData).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.username").value(this.customer.getUsername()));

		verify(customerValidation, times(1)).insertValidation(customer);
		verify(customerService, times(1)).save(customer);
	}

	@Test
	public void addCustomerWithNullValues() throws Exception {

		when(customerService.save(this.customer)).thenReturn(this.customer);

		this.customer.setUsername(null);

		String jsonData = JSONConvertion.convertObjectToJsonBytes(this.customer);

		mockMvc.perform(post("/api/customer").content(jsonData).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.usernameError").exists());

	}

	@Test
	public void addCustomerWithInvalidEmailValues() throws Exception {

		when(customerService.save(this.customer)).thenReturn(this.customer);

		this.customer.setEmail("myemail");

		String jsonData = JSONConvertion.convertObjectToJsonBytes(this.customer);

		mockMvc.perform(post("/api/customer").content(jsonData).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.emailError").exists());

	}

	@Test
	public void addCustomerWithDuplicateCustomerValues() throws Exception {

		Map<String, String> errorMessages = new HashMap<String, String>();

		errorMessages.put("emailError", "");
		errorMessages.put("mobileError", "");

		when(customerValidation.insertValidation(customer)).thenReturn(errorMessages);

		String jsonData = JSONConvertion.convertObjectToJsonBytes(this.customer);

		mockMvc.perform(post("/api/customer").content(jsonData).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.emailError").exists()).andExpect(jsonPath("$.mobileError").exists());

		verify(customerValidation, times(1)).insertValidation(customer);

	}

	@Test
	public void addCustomerWithInternalServerError() throws Exception {

		when(customerService.save(customer)).thenReturn(null);
		when(customerValidation.insertValidation(customer)).thenReturn(new HashMap<String, String>());

		String jsonData = JSONConvertion.convertObjectToJsonBytes(this.customer);

		mockMvc.perform(post("/api/customer").content(jsonData).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isInternalServerError());

		verify(customerValidation, times(1)).insertValidation(customer);
		verify(customerService, times(1)).save(customer);

	}
}
