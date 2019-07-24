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

	public Customer update(Customer customer) {
		try {
			return customerRepository.saveAndFlush(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public Customer login(String emailOrUsername, String password) {
		try {
			return customerRepository.login(emailOrUsername, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Customer getCustomer(Long id) {
		try {
			return customerRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Customer getCustomerByEmail(String email) {
		try {
			return customerRepository.findByEmail(email);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Customer getCustomerByUsername(String username) {
		try {
			return customerRepository.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Customer getCustomerByMobile(String mobile) {
		try {
			return customerRepository.findByMobile(mobile);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
