package com.exportimport.restapi.test.services.customer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.exportimport.restapi.RestapiApplication;
import com.exportimport.restapi.model.Customer;
import com.exportimport.restapi.repository.CustomerRepository;
import com.exportimport.restapi.service.CustomerService;
import com.exportimport.restapi.util.CustomerInstanceProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestapiApplication.class)
public class AddCustomerTest {

	@InjectMocks
	private CustomerService customerSerivce;

	@Mock
	private CustomerRepository customerRepository;

	private Customer customer;

	@Before
	public void setup() {
		customer = CustomerInstanceProvider.getCustomer();
	}

	@Test
	public void addCustomerTest() {
		when(customerRepository.save(this.customer)).thenReturn(this.customer);
		assertNotNull(customerSerivce.save(customer));
	}

	@Test
	public void addCustomerWithUsernameNull() {
		when(customerRepository.save(this.customer)).thenThrow(ConstraintViolationException.class);
		assertNull(customerSerivce.save(customer));
	}
	
	
	@Test
	public void addCustomerWithDuplicateUsername() {
		when(customerRepository.save(this.customer)).thenThrow(DataIntegrityViolationException.class);
		assertNull(customerSerivce.save(customer));
	}

}
