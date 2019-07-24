package com.exportimport.restapi.exception.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class CustomerValidationException extends RuntimeException {

	private Errors errors;

	public CustomerValidationException(Errors errors) {
		this.errors = errors;
	}

	public Map<String, String> getErrorsMessages() {
		return this.prepareErroMessages();
	}

	private Map<String, String> prepareErroMessages() {

		Map<String, String> errorMessages = new HashMap<String, String>();
		for (FieldError error : errors.getFieldErrors()) {
			errorMessages.put(error.getField() + "Error", error.getDefaultMessage());
		}
		return errorMessages;
	}

}
