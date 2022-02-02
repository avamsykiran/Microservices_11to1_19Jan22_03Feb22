package com.cts.budgettracker.statement.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Transaction implements Comparable<Transaction> {

	private Long txnId;
	private String header;
	private Double amount;
	private TransactionType type;
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate dateOfTransaction;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(Long txnId, String header, Double amount, TransactionType type, LocalDate dateOfTransaction) {
		super();
		this.txnId = txnId;
		this.header = header;
		this.amount = amount;
		this.type = type;
		this.dateOfTransaction = dateOfTransaction;
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public LocalDate getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDate dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	@Override
	public int compareTo(Transaction arg0) {
		return this.txnId.compareTo(arg0.txnId);
	}

	
}
