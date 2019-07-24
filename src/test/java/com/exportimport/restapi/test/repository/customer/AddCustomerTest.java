package com.exportimport.restapi.test.repository.customer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import com.exportimport.restapi.RestapiApplication;
import com.exportimport.restapi.model.Customer;
import com.exportimport.restapi.repository.CustomerRepository;
import com.exportimport.restapi.util.CustomerInstanceProvider;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringJUnitConfig(classes = RestapiApplication.class)
public class AddCustomerTest {

	@Autowired
	private CustomerRepository customerRepository;

	private Customer customer;

	@Before
	public void setup() {
		customer = CustomerInstanceProvider.getCustomer();
	}

	@Test
	public void addCustomerTest() {
		assertNotNull(customerRepository.save(customer));
	}

	@Test(expected = ConstraintViolationException.class)
	public void addCustomerWithNullValue() {
		customer.setUsername(null);
		assertNull(customerRepository.save(customer));
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void addCustomerWithDuplicateValues() {
		customerRepository.save(this.customer);
		Customer customer = CustomerInstanceProvider.getCustomerForDuplicate();
		customer.setUsername(this.customer.getUsername());
		assertNull(customerRepository.save(customer));
	}
	

}
