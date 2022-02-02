package com.cts.budgettracker.statement.model;

public class AccountHolderTxnServiceBoundedContext {

	private Long ahId;
	private Double currentBalance;
	
	public AccountHolderTxnServiceBoundedContext() {
		// TODO Auto-generated constructor stub
	}

	public AccountHolderTxnServiceBoundedContext(Long ahId, Double currentBalance) {
		super();
		this.ahId = ahId;
		this.currentBalance = currentBalance;
	}

	public Long getAhId() {
		return ahId;
	}

	public void setAhId(Long ahId) {
		this.ahId = ahId;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}	
	
}
