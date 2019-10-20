package com.assignment.contactservice.exception;

public class ContactAlreadyExistsException extends RuntimeException {
	
	public ContactAlreadyExistsException(String emailId) {
		super("Contact with Email ID: " + emailId + " already exists");
	}
}
