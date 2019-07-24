package com.exportimport.restapi.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.exportimport.restapi.model.Customer;

@Service
public class CustomerInstanceProvider {
	
	
	public static Customer getCustomer() {
		Customer customer = new Customer();
		customer.setUsername("ebinezer");
		customer.setEmail("ebinezer@gmail.com");
		customer.setMobile("9494216610");
		customer.setPassword("P@ssw0rd#123");
		customer.setEnabled(true);
		
		return customer;
	}
	
	public static Customer getCustomerForDuplicate() {
		Customer customer = new Customer();
		customer.setUsername("rakesh");
		customer.setEmail("rakesh@gmail.com");
		customer.setMobile("9494216611");
		customer.setPassword("P@ssw0rd#123");
		customer.setEnabled(true);
		
		return customer;
	}

}
