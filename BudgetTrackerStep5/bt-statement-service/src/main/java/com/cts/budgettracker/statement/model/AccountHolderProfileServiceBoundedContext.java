package com.cts.budgettracker.statement.model;

public class AccountHolderProfileServiceBoundedContext {

	private Long ahId;	
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNumber;
	
	public AccountHolderProfileServiceBoundedContext() {}

	public AccountHolderProfileServiceBoundedContext(Long ahId, String firstName, String lastName, String emailId, String mobileNumber) {
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
