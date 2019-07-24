package com.exportimport.restapi.customer.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.transaction.TransactionalException;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exportimport.restapi.model.Customer;
import com.exportimport.restapi.repository.CustomerRepository;
import com.exportimport.restapi.util.CustomerInstanceProvider;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UpdateCustomerTest {

	@Autowired
	private CustomerRepository customerRepository;

	private Customer customer;

	@Before
	public void setup() {
		customer = CustomerInstanceProvider.getCustomer();
		customerRepository.save(customer);
	}

	@Test
	public void updateCustomerTest() {
		Customer customer = customerRepository.findById(this.customer.getId()).get();
		customer.setUsername("ebinezerp");
		assertNotNull(customerRepository.save(customer));
	}

	@Test(expected = Exception.class)
	@Transactional(propagation = Propagation.SUPPORTS)
	public void updateCustomerWithNullUsernameTest() {
		Customer customer = customerRepository.findById(this.customer.getId()).get();
		customer.setUsername(null);
		assertNull(customerRepository.save(customer));
	}

	/*
	 * @Test(expected = DataIntegrityViolationException.class)
	 * 
	 * @Transactional(propagation = Propagation.NOT_SUPPORTED) public void
	 * updateCustomerWithDuplicateUsernameTest() { Customer customer =
	 * CustomerInstanceProvider.getCustomerForDuplicate();
	 * customerRepository.save(customer); Customer fetechedCustomer =
	 * customerRepository.findById(customer.getId()).get();
	 * fetechedCustomer.setUsername(this.customer.getUsername());
	 * assertNull(customerRepository.save(fetechedCustomer)); }
	 */
	
	
	
	
}
