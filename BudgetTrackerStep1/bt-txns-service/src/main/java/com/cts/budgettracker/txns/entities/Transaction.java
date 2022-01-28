package com.cts.budgettracker.txns.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "transactions")
public class Transaction implements Comparable<Transaction> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long txnId;
	private String header;
	private Double amount;
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfTransaction;
	@ManyToOne
	private AccountHolder holder;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(Long txnId, String header, Double amount, TransactionType type, LocalDate dateOfTransaction,
			AccountHolder holder) {
		super();
		this.txnId = txnId;
		this.header = header;
		this.amount = amount;
		this.type = type;
		this.dateOfTransaction = dateOfTransaction;
		this.holder = holder;
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

	public AccountHolder getHolder() {
		return holder;
	}

	public void setHolder(AccountHolder holder) {
		this.holder = holder;
	}

	@Override
	public int compareTo(Transaction o) {
		return this.txnId == null ? 0 : this.txnId.compareTo(o.txnId);
	}

	@Override
	public String toString() {
		return "Transaction [txnId=" + txnId + ", header=" + header + ", amount=" + amount + ", type=" + type
				+ ", dateOfTransaction=" + dateOfTransaction + ", holder=" + holder + "]";
	}
	
	
}
