package com.cts.budgettracker.txns.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="account_holders")
public class AccountHolder {

	@Id
	private Long ahId;
	private Double currentBalance;
	
	@JsonIgnore
	@OneToMany(mappedBy = "holder",fetch = FetchType.LAZY)
	private Set<Transaction> transactions;
	
	public AccountHolder() {
		// TODO Auto-generated constructor stub
	}

	public AccountHolder(Long ahId, Double currentBalance, Set<Transaction> transactions) {
		super();
		this.ahId = ahId;
		this.currentBalance = currentBalance;
		this.transactions = transactions;
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

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "AccountHolder [ahId=" + ahId + ", currentBalance=" + currentBalance + "]";
	}
	
	
}
