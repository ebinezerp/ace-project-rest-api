package com.exportimport.restapi.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exportimport.restapi.exception.customer.CustomerExistenceException;
import com.exportimport.restapi.exception.customer.CustomerValidationException;

@ControllerAdvice
public class RestApiExceptionHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerValidationException.class)
	public ResponseEntity<Map<String, String>> customerValidationExceptionHandler(CustomerValidationException ex) {
		return new ResponseEntity<Map<String, String>>(ex.getErrorsMessages(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	
	@ExceptionHandler(CustomerExistenceException.class)
	public ResponseEntity<Map<String, String>> customerExistenceExceptionHandler(CustomerExistenceException ex) {
		return new ResponseEntity<Map<String,String>>(ex.getErrorMessages(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
