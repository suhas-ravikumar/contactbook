package com.assignment.contactservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.assignment.contactservice.entity.Contact;
import com.assignment.contactservice.exception.ContactAlreadyExistsException;
import com.assignment.contactservice.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepository;


	@Override
	public Contact findByEmailId(String emailId) {
		Optional<Contact> contact = contactRepository.findById(emailId);

		if (contact.isPresent())
			return contact.get();
		return new Contact();
	}

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		if(contacts == null)
			return new ArrayList<>();
		return contacts;
	}

	@Override
	public void addContact(Contact contact) {
		if(contactRepository.existsById(contact.getEmailId())) {
			throw new ContactAlreadyExistsException(contact.getEmailId());
		}
		contactRepository.save(contact);
	}
	
	@Override
	public void addContactBulk(List<Contact> contactList) {
		contactRepository.saveAll(contactList);
	}

	@Override
	public boolean updateContact(Contact contact) {
		if(contactRepository.existsById(contact.getEmailId())) {
			contactRepository.save(contact);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteContact(String emailId) {
		if(contactRepository.existsById(emailId)) {
		contactRepository.deleteById(emailId);
		return true;
		}
		return false;
	}

	@Override
	public List<Contact> search(String emailId, String name, String page, String size) {
		
		if(page == null || "".equals(page)) {
			page = "0";
		}
		if(size == null || "".equals(size)) {
			size = "10";
		}
		
		boolean isPageNumeric = page.chars().allMatch( Character::isDigit );
		boolean isSizeNumeric = size.chars().allMatch( Character::isDigit );
		if(!isPageNumeric) page = "0";
		if(!isSizeNumeric) size = "10";
		
			
		List<Contact> contactList = new ArrayList<>();
		if (null == emailId && null == name)
			return contactList;
		else if (emailId != null && name == null) {
			Optional<Contact> contactOptional = contactRepository.findById(emailId);
			if (contactOptional.isPresent()) {
				contactList.add(contactOptional.get());
			}
			return contactList;
		}
		else if(emailId == null && name != null) {
			return contactRepository.findByName(name, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)));
		}
		else
			return contactRepository.findByEmailIdAndName(emailId, name, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)));

	}

	

}
