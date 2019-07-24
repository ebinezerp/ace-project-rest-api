package com.exportimport.restapi.exception.customer;

import java.util.Map;

public class CustomerExistenceException extends RuntimeException {
	
	private Map<String,String> errorMessages;
	
	public CustomerExistenceException(Map<String,String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}
	
}
