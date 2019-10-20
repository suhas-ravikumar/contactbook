package com.assignment.contactservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.assignment.contactservice.entity.Contact;
import com.assignment.contactservice.exception.ContactNotFoundException;
import com.assignment.contactservice.service.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Contact Book", description="Basic CRUD Operations on Contact data")
public class ContactController {
	
	@Autowired
	ContactService contactService;
	
	@ApiOperation(value = "Checks the Heartbeat of the application", response = String.class)
	@GetMapping("/heartbeat")
	public ResponseEntity<String> heartbeat() {
		
		return ResponseEntity.ok().body("Application Up and Running");
		
	}
	
	@ApiOperation(value = "Retrieve a list of all the contacts available", response = List.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved the Contact List"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden")
		})
	@GetMapping("/contacts")
	public List<Contact> getAllContacts() {
		
		List<Contact> contactList = contactService.getAllContacts();
		return contactList;
		
	}
	
	@ApiOperation(value = "Retrieve a single contact corresponding to the passed Email ID", response = Contact.class)
	@GetMapping("/contacts/{emailId}")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved the Contact"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 404, message = "Contact with the Email ID not found")
		})
	public Contact getContactById(@ApiParam(value = "Email ID of the Contact to be retrieved", required = true) @PathVariable String emailId) throws ContactNotFoundException {
		
		Contact contact = contactService.findByEmailId(emailId);
		if(contact.getEmailId() != null && !("").equals(contact.getEmailId()))
			return contact;
		throw new ContactNotFoundException(emailId);
		
	}
	
	@ApiOperation(value = "Create a new contact")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Successfully created the Contact"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 409, message = "Contact with the Email ID already exists")
		})
	@PostMapping("/contacts")
	public ResponseEntity<String> createContact(@ApiParam(value = "Content type", required = false) @RequestHeader(name = "Content-Type") String headerPersist, 
			@ApiParam(value = "Contact JSON to be created", required = true) @RequestBody Contact newContact) {
		
		contactService.addContact(newContact);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		        .buildAndExpand(newContact.getEmailId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@ApiOperation(value = "Creates a contacts in bulk for a list of passed contacts")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Successfully created the Contact"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 409, message = "Contact with the Email ID already exists")
		})
	@PostMapping("/contactsbulk")
	public ResponseEntity<String> createContactBulk(@ApiParam(value = "Content type", required = false) @RequestHeader(name = "Content-Type") String headerPersist, 
			@ApiParam(value = "List of contacts that have to be created", required = true) @RequestBody List<Contact> contactList) {
		
		contactService.addContactBulk(contactList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		        .build().toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@ApiOperation(value = "Updates the contact, if exists")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully updated the Contact"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 404, message = "Contact with the Email ID not found")
		})
	@PutMapping("/contacts/{emailId}")
	public ResponseEntity<String> updateContact(@ApiParam(value = "Content type", required = false) @RequestHeader(name = "Content-Type") String headerPersist, @ApiParam(value = "Email ID of the contact to be updated", required = true) @PathVariable String emailId,
			@ApiParam(value = "Properties of the contact to be updated", required = true) @RequestBody Contact contactToUpdate) {
		
		if(contactToUpdate.getEmailId() == null) contactToUpdate.setEmailId(emailId);
		if(contactService.updateContact(contactToUpdate))
			return ResponseEntity.ok().body("Contact with Email ID " + emailId + " updated successfully");
		throw new ContactNotFoundException(contactToUpdate.getEmailId());
	}
	
	@ApiOperation(value = "Deletes the contact having the Passed Email ID")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully deleted the Contact"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 404, message = "Contact with the Email ID not found")
		})
	@DeleteMapping("/contacts/{emailId}")
	public ResponseEntity<String> deleteContact(@ApiParam(value = "Email ID of the contact to be deleted", required = true) @PathVariable String emailId) {
		
		if(contactService.deleteContact(emailId))
			return ResponseEntity.ok().body("Contact with Email ID " + emailId + " deleted successfully");
		throw new ContactNotFoundException(emailId);
		
	}
	
	@ApiOperation(value = "Searches for contacts based on the email ID or Name", response = List.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "List of contacts matching the search criteria"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden")
		})
	@GetMapping("/contacts/search")
	public List<Contact> searchByEmailOrName(@ApiParam(value = "Email ID of the contact to be searched", required = false) @RequestParam(required=false) String emailId, 
			@ApiParam(value = "Name of the contact to be searched", required = false) @RequestParam(required=false) String name, 
			@ApiParam(value = "Page Number, Starting with 0", required = false) @RequestParam(required=false) String page,
			@ApiParam(value = "Page Size, defaulted to 10", required = false) @RequestParam(required=false) String size) {
		List<Contact> contactList = contactService.search(emailId, name, page, size);
		return contactList;
		
		
	}
	

}
