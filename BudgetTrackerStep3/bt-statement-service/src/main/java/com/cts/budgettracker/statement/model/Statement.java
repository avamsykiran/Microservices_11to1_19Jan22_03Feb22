package com.cts.budgettracker.statement.model;

import java.time.LocalDate;
import java.util.Set;

public class Statement {
	
	private long ahId;	
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNumber;
	private double currentBalance;
	private double totalCredit;
	private double totalDebit;
	private double statementBalance;
	private Set<Transaction> txns;
	private LocalDate from;
	private LocalDate to;

	public Statement() {
		// TODO Auto-generated constructor stub
	}

	public Statement(long ahId, String firstName, String lastName, String emailId, String mobileNumber,
			double currentBalance, double totalCredit, double totalDebit, double statementBalance,
			Set<Transaction> txns, LocalDate from, LocalDate to) {
		super();
		this.ahId = ahId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.currentBalance = currentBalance;
		this.totalCredit = totalCredit;
		this.totalDebit = totalDebit;
		this.statementBalance = statementBalance;
		this.txns = txns;
		this.from = from;
		this.to = to;
	}


	public long getAhId() {
		return ahId;
	}

	public void setAhId(long ahId) {
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

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public double getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(double totalCredit) {
		this.totalCredit = totalCredit;
	}

	public double getTotalDebit() {
		return totalDebit;
	}

	public void setTotalDebit(double totalDebit) {
		this.totalDebit = totalDebit;
	}

	public double getStatementBalance() {
		return statementBalance;
	}

	public void setStatementBalance(double statementBalance) {
		this.statementBalance = statementBalance;
	}

	public Set<Transaction> getTxns() {
		return txns;
	}

	public void setTxns(Set<Transaction> txns) {
		this.txns = txns;
	}

	public LocalDate getFrom() {
		return from;
	}

	public void setFrom(LocalDate from) {
		this.from = from;
	}

	public LocalDate getTo() {
		return to;
	}

	public void setTo(LocalDate to) {
		this.to = to;
	}
	
	
}
