package com.cts.budgettracker.profile.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account_holders")
public class AccountHolder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ah_id")
	private Long ahId;
	@Column(name="fnm",nullable = false)
    private String firstName;
	@Column(name="lnm",nullable = false)
    private String lastName;
	@Column(name="eid",nullable = false,unique = true)
    private String emailId;
	@Column(name="mno",nullable = false,unique = true)
    private String mobileNumber;
	
	public AccountHolder() {}

	public AccountHolder(Long ahId, String firstName, String lastName, String emailId, String mobileNumber) {
		super();
		this.ahId = ahId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
	}

	public Long getAhId() {
		return ahId;
	}

	public void setAhId(Long ahId) {
		this.ahId = ahId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
