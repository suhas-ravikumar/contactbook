package com.assignment.contactservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import com.assignment.contactservice.entity.Contact;
import com.assignment.contactservice.repository.ContactRepository;

@WebMvcTest(ContactServiceImpl.class)
class ContactServiceImplTest {

	List<Contact> contactListExpected;

	@Autowired
	ContactService contactService;

	@MockBean
	private ContactRepository contactRepository;

	@Test
	void testSearchNameFound() {

		contactListExpected = new ArrayList<>();

		Contact contact = new Contact("john.doe@abc.com", "John Doe", 9876543210l, "Google", "Frontend Engineer",
				"Koramangala", "Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("john.doe01@abc.com", "John Doe", 5678912345l, "Microsoft", "Engineer", "Whitefield",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);

		Mockito.when(contactRepository.findByName("John Doe",
				PageRequest.of(Integer.parseInt("0"), Integer.parseInt("10")))).thenReturn(contactListExpected);

		List<Contact> contactListActual = contactService.search(null, "John Doe", null, null);
		assertEquals(2, contactListActual.size(), "Array size assertion failed");
		assertEquals("john.doe@abc.com", contactListActual.get(0).getEmailId(), "Search by Name failed");
		assertEquals("john.doe01@abc.com", contactListActual.get(1).getEmailId(), "Search by Name failed");

	}

	@Test
	void testSearchNameNotFound() {

		Mockito.when(contactRepository.findByName("John Doe",
				PageRequest.of(Integer.parseInt("0"), Integer.parseInt("10")))).thenReturn(new ArrayList<>());

		List<Contact> contactListActual = contactService.search(null, "Dummy", null, null);

		assertEquals(0, contactListActual.size(), "Search By Name Not found Assertion Failed ");
	}

	@Test
	void testSearchEmailFound() {

		Contact contact = new Contact("jane.doe@gmail.com", "Jane Doe", 12345657890l, "Apple", "Engineer", "Marathalli",
				"Bangalore", "KA", 562114);

		Mockito.when(contactRepository.findById("jane.doe@gmail.com")).thenReturn(Optional.of(contact));

		List<Contact> contactListActual = contactService.search("jane.doe@gmail.com", null, null, null);
		assertEquals(1, contactListActual.size(), "Array size assertion failed");
		assertEquals("Jane Doe", contactListActual.get(0).getName(), "Search by Email Id Assertion failed");
	}

	@Test
	void testSearchEmailNotFound() {

		Mockito.when(contactRepository.findById("notFound@gmail.com")).thenReturn(Optional.empty());

		List<Contact> contactListActual = contactService.search("notFound@gmail.com", null, null, null);

		assertEquals(0, contactListActual.size(), "Search By Email ID Not found Assertion Failed ");
	}

	@Test
	void testSearchEmptyParms() {

		List<Contact> contactListActual = contactService.search(null, null, null, null);

		assertEquals(0, contactListActual.size(), "Empty Email and Name Assertion Failed ");
	}

	@Test
	void testSearchWithPagination() {

		contactListExpected = new ArrayList<>();

		Contact contact = new Contact("user1@abc.com", "User", 9876543210l, "Google", "Frontend Engineer",
				"Koramangala", "Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user2@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);

		Mockito.when(contactRepository.findByName("User",
				PageRequest.of(Integer.parseInt("0"), Integer.parseInt("2")))).thenReturn(contactListExpected);

		List<Contact> contactListActual = contactService.search(null, "User", "0", "2");

		assertEquals(2, contactListActual.size(), "Contact Search with pagination parameters test failed");

		assertEquals("user1@abc.com", contactListActual.get(0).getEmailId(),
				"Contact Search with pagination parameters test failed");
	}

	@Test
	void testSearchWithoutPagination() {

		contactListExpected = new ArrayList<>();

		Contact contact = new Contact("user1@abc.com", "User", 9876543210l, "Google", "Frontend Engineer",
				"Koramangala", "Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user2@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user3@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user4@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user5@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user6@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user7@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user8@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user9@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("user10@abc.com", "User", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);

		Mockito.when(contactRepository.findByName("User",
				PageRequest.of(Integer.parseInt("0"), Integer.parseInt("10")))).thenReturn(contactListExpected);

		List<Contact> contactListActual = contactService.search(null, "User", null, null);

		assertEquals(10, contactListActual.size(), "Contact Search without pagination parameters test failed");

		assertEquals("user1@abc.com", contactListActual.get(0).getEmailId(),
				"Contact Search without pagination parameters test failed");
	}

	@Test
	void testFindByEmailIdFound() {
		Contact contact = new Contact("jane.doe@gmail.com", "Jane Doe", 12345657890l, "Apple", "Engineer", "Marathalli",
				"Bangalore", "KA", 562114);

		Mockito.when(contactRepository.findById("jane.doe@gmail.com")).thenReturn(Optional.of(contact));

		Contact contactActual = contactService.findByEmailId("jane.doe@gmail.com");
		assertEquals("Jane Doe", contactActual.getName(), "Find by Email Id Assertion failed");
	}

	@Test
	void testFindByEmailIdNotFound() {
		Mockito.when(contactRepository.findById("notFound@gmail.com")).thenReturn(Optional.empty());

		Contact actualContact = contactService.findByEmailId("notFound@gmail.com");

		assertEquals(null, actualContact.getEmailId(), "Find By Email ID Not found Assertion Failed ");
	}

	@Test
	void testFindByEmailIdAndName() {

		contactListExpected = new ArrayList<>();

		Contact contact = new Contact("john.doe@abc.com", "John Doe", 9876543210l, "Google", "Frontend Engineer",
				"Koramangala", "Bangalore", "KA", 560066);
		contactListExpected.add(contact);

		Mockito.when(contactRepository.findByEmailIdAndName("john.doe@abc.com", "John Doe",
				PageRequest.of(Integer.parseInt("0"), Integer.parseInt("2")))).thenReturn(contactListExpected);

		List<Contact> contactListActual = contactService.search("john.doe@abc.com", "John Doe", "0", "2");

		assertEquals(1, contactListActual.size(), "Contact Search test by both Email ID and Name failed");

		assertEquals("john.doe@abc.com", contactListActual.get(0).getEmailId(),
				"Contact Search test by both Email ID and Name failed");
	}

	@Test
	void testGetAllContactsFound() {

		contactListExpected = new ArrayList<>();
		Contact contact = new Contact("jane.doe@gmail.com", "Jane Doe", 12345657890l, "Apple", "Engineer", "Marathalli",
				"Bangalore", "KA", 562114);
		contactListExpected.add(contact);
		contact = new Contact("john.doe@abc.com", "John Doe", 9876543210l, "Google", "Frontend Engineer", "Koramangala",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);
		contact = new Contact("john.doe01@abc.com", "John Doe", 5678912345l, "Microsoft", "Engineer", "Whitefield",
				"Bangalore", "KA", 560066);
		contactListExpected.add(contact);

		Mockito.when(contactRepository.findAll()).thenReturn(contactListExpected);

		List<Contact> contactListActual = contactService.getAllContacts();
		assertEquals(3, contactListActual.size(), "Array size assertion failed");
		assertEquals("jane.doe@gmail.com", contactListActual.get(0).getEmailId(), "Get All Assertion Failed");
		assertEquals("john.doe@abc.com", contactListActual.get(1).getEmailId(), "Get All Assertion Failed");
		assertEquals("john.doe01@abc.com", contactListActual.get(2).getEmailId(), "Get All Assertion Failed");
	}

	@Test
	void testGetAllContactsNotFound() {

		Mockito.when(contactRepository.findAll()).thenReturn(new ArrayList<>());

		List<Contact> contactListActual = contactService.getAllContacts();
		assertEquals(0, contactListActual.size(), "Array size assertion failed");
	}

}
