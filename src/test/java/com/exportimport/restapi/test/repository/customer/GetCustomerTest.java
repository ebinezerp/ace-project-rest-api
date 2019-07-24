package com.exportimport.restapi.test.repository.customer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.exportimport.restapi.model.Customer;
import com.exportimport.restapi.repository.CustomerRepository;
import com.exportimport.restapi.util.CustomerInstanceProvider;

@DataJpaTest(showSql = true)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GetCustomerTest {

	@Autowired
	private CustomerRepository customerRepository;

	private Customer customer;

	@Before
	public void setup() {
		customer = CustomerInstanceProvider.getCustomer();
		customerRepository.save(customer);
	}

	@Test
	public void getCustomerTest() {
		assertThat(customerRepository.findById(this.customer.getId()).get()).isEqualTo(this.customer);
	}

	@Test(expected = Exception.class)
	public void getCustomerTestWithNonExistingId() {
		assertThat(customerRepository.findById(100L).get()).isNull();
	}

	@Test(expected = Exception.class)
	public void getCustomerTestWithNullValue() {
		assertThat(customerRepository.findById(null).get()).isNull();
	}

	@Test
	public void getCustomers() {
		Customer customer = CustomerInstanceProvider.getCustomerForDuplicate();
		customerRepository.save(customer);
		List<Customer> customers = customerRepository.findAll();
		assertThat(customers).isNotNull();
		assertThat(customers.size()).isEqualTo(2);

	}

	@Test
	public void getCustomerByEmailTest() {
		assertThat(customerRepository.findByEmail(this.customer.getEmail())).isEqualTo(this.customer);
	}

	@Test
	public void getCustomerByInvaildEmailTest() {
		assertThat(customerRepository.findByEmail("mymail@gmail.com")).isNull();
	}

	@Test
	public void loginWithEmailTest() {
		assertThat(customerRepository.login(this.customer.getEmail(), this.customer.getPassword()))
				.isEqualTo(this.customer);
	}

	@Test
	public void loginWithEmailAndInvalidPasswordTest() {
		assertThat(customerRepository.login(this.customer.getEmail(), "mypassword")).isNull();
	}

	@Test
	public void loginWithInvalidEmailTest() {
		assertThat(customerRepository.login("myemail@gmail.com", this.customer.getPassword())).isNull();
	}

	@Test
	public void loginWithUsernameTest() {
		assertThat(customerRepository.login(this.customer.getUsername(), this.customer.getPassword()))
				.isEqualTo(this.customer);
	}

	@Test
	public void loginWithUsernameAndInvalidPasswordTest() {
		assertThat(customerRepository.login(this.customer.getUsername(), "mypassword")).isNull();
	}

	@Test
	public void loginWithInvalidUsernameTest() {
		assertThat(customerRepository.login("myusername", this.customer.getPassword())).isNull();
	}

}
