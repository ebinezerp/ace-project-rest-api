package com.exportimport.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exportimport.restapi.model.Customer;
import com.exportimport.restapi.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public Customer save(Customer customer) {
		try {
			return customerRepository.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
