package com.assignment.contactservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contactdata")
public class Contact {

	public Contact() {
	}

	public Contact(String emailId, String name, long mobileNo, String organization, String designation,
			String streetName, String city, String state, int pinCode) {
		super();
		this.emailId = emailId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.organization = organization;
		this.designation = designation;
		this.streetName = streetName;
		this.city = city;
		this.stateName = state;
		this.pinCode = pinCode;
	}
	
	@Id
	private String emailId;
	private String name;
	private long mobileNo;
	private String organization;
	private String designation;
	private String streetName;
	private String city;
	private String stateName;
	private int pinCode;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String state) {
		this.stateName = state;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	
}
