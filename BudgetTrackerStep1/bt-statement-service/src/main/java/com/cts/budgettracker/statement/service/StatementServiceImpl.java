package com.cts.budgettracker.statement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.budgettracker.statement.exception.StatementException;
import com.cts.budgettracker.statement.model.AccountHolderProfileServiceBoundedContext;
import com.cts.budgettracker.statement.model.AccountHolderTxnServiceBoundedContext;
import com.cts.budgettracker.statement.model.Statement;
import com.cts.budgettracker.statement.model.Transaction;
import com.cts.budgettracker.statement.model.TransactionType;

public class StatementServiceImpl implements StatementService {
	
	@Autowired
	private ProfileServiceClient profileClient;
	
	@Autowired
	private TxnsServiceClient txnsClient;

	@Override
	public Statement getStatement(long ahId, LocalDate from, LocalDate to) throws StatementException {
		
		AccountHolderProfileServiceBoundedContext ahps = profileClient.getAccountHolderById(ahId);
		
		if(ahps==null) {
			throw new StatementException("Account Not Found");
		}
		
		AccountHolderTxnServiceBoundedContext ahts = txnsClient.getAccountHolder(ahId);
		
		if(ahts==null) {
			throw new StatementException("No transactions found for the accoutn holder");
		}
		
		List<Transaction> txns = txnsClient.getTransactions(ahId, from, to);
		
		if(txns==null || txns.isEmpty()) {
			throw new StatementException("No transactions found for the account holder in the given period");
		}
		
		double totalCredit= totalOf(txns,TransactionType.CREDIT);
		double totalDebit=totalOf(txns,TransactionType.DEBIT);
		double statementBalance=totalCredit-totalDebit;
		
		return new Statement(ahId, 
				ahps.getFirstName(), ahps.getLastName(), ahps.getEmailId(), ahps.getMobileNumber(), 
				ahts.getCurrentBalance(), totalCredit, totalDebit, statementBalance, 
				new TreeSet<>(txns), from, to);
	}

	private double totalOf(List<Transaction> txns,TransactionType type) {
		return txns.stream().
						filter(t -> t.getType()==type).
						map( t -> t.getAmount()).
						reduce((a1,a2)->a1+a2).orElse(0.0);
	}
}
