package com.exportimport.restapi.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exportimport.restapi.model.Customer;
import com.exportimport.restapi.service.CustomerService;

@Component
public class CustomerValidation {
	
	@Autowired
	private CustomerService customerService;
	
	public Map<String, String> insertValidation(Customer customer) {
		
		Map<String, String> errorMessages = new HashMap<String, String>();
		
		if (customerService.getCustomerByEmail(customer.getEmail()) != null) {
			errorMessages.put("emailError", "Email is already existed");
		}
		
		if (customerService.getCustomerByUsername(customer.getUsername()) != null) {
			errorMessages.put("usernameError", "Username is already existed");
		}
		
		if (customerService.getCustomerByMobile(customer.getMobile()) != null) {
			errorMessages.put("mobileError", "Mobile is already existed");
		}
		
		return errorMessages;
	}

}
