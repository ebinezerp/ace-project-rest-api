package com.exportimport.restapi.test.repository.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

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
		customer = customerRepository.findById(this.customer.getId()).get();
	}

	@Test
	public void updateCustomerTest() {
		customer.setUsername("ebinezerp");
		customer = customerRepository.save(customer);
		assertNotNull(customer);
		assertNotNull(customerRepository.findById(customer.getId()).get());
		assertThat(customerRepository.findById(customer.getId()).get().getUsername()).isEqualTo("ebinezerp");
	}

	@Test(expected = Exception.class)
	public void updateCustomerWithNullUsernameTest() {
		customer.setUsername(null);
		assertNull(customerRepository.saveAndFlush(customer));
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void updateCustomerWithDuplicateUsernameTest() {
		Customer duplicateCustomer = CustomerInstanceProvider.getCustomerForDuplicate();
		customerRepository.save(duplicateCustomer);
		customer.setUsername(duplicateCustomer.getUsername());
		assertNull(customerRepository.saveAndFlush(customer));
	}

}
