package com.assignment.contactservice.error.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.contactservice.exception.ContactAlreadyExistsException;
import com.assignment.contactservice.exception.ContactNotFoundException;
import com.assignment.contactservice.exception.ErrorResponse;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(ContactNotFoundException.class)
	ResponseEntity<ErrorResponse> contactNotFoundHandler(ContactNotFoundException ex) {
		
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setErrorMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("Content-Type", 
	      "application/json");

        return new ResponseEntity<>(error, responseHeaders, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(ContactAlreadyExistsException.class)
	ResponseEntity<ErrorResponse> contactAlreadyExistsHandler(ContactAlreadyExistsException ex) {
		
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setErrorMessage(ex.getMessage());
		error.setStatus(HttpStatus.CONFLICT.value());
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("Content-Type", 
	      "application/json");

        return new ResponseEntity<>(error, responseHeaders, HttpStatus.CONFLICT);

	}

}
