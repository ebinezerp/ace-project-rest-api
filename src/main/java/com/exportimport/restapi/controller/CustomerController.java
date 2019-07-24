package com.exportimport.restapi.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.exportimport.restapi.exception.customer.CustomerExistenceException;
import com.exportimport.restapi.exception.customer.CustomerValidationException;
import com.exportimport.restapi.model.Customer;
import com.exportimport.restapi.service.CustomerService;
import com.exportimport.restapi.util.CustomerValidation;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerValidation customerValidation;

	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer, Errors errors) {
		if (errors.hasErrors()) {
			throw new CustomerValidationException(errors);
		}

		Map<String, String> errorMessages = customerValidation.insertValidation(customer);

		if (errorMessages.size() > 0) {
			throw new CustomerExistenceException(errorMessages);
		}

		if (customerService.save(customer) == null) {
			new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "some error occured");
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);

	}
	
	@GetMapping
	public ResponseEntity<Customer> getCustomer(){
		return new ResponseEntity<Customer>(new Customer(),HttpStatus.OK);
	}

}
