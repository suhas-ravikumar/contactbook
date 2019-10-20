package com.assignment.contactservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.contactservice.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
	
	List<Contact> findByName(String name,Pageable pageable);
	
	@Query("SELECT c FROM Contact c WHERE c.emailId = ?1 and c.name = ?2")
	List<Contact> findByEmailIdAndName(String emailId, String name, Pageable pageable);
	
}
