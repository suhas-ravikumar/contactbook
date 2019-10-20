package com.assignment.contactservice.service;

import java.util.List;

import com.assignment.contactservice.entity.Contact;

public interface ContactService {
	
	
	Contact findByEmailId(String emailId);
	
	List<Contact> getAllContacts();
	
	void addContact(Contact contact);
	
	void addContactBulk(List<Contact> contactList);

	boolean updateContact(Contact contact);
	
	boolean deleteContact(String emailId);

	List<Contact> search(String emailId, String name, String page, String size);

}
