package com.assignment.contactservice.exception;

public class ContactNotFoundException extends RuntimeException {

	public ContactNotFoundException(String emailId) {
		super("Could not find contact with email: " + emailId);
	}
}
