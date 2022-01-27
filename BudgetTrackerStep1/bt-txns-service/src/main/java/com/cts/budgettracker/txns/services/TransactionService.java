package com.cts.budgettracker.txns.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.TransactionalException;

import com.cts.budgettracker.txns.entities.Transaction;

public interface TransactionService {
	List<Transaction> getAllbyAccountHolderId(Long ahId) throws TransactionalException;
	List<Transaction> getAllbyAccountHolderIdOfPeiod(Long ahId,LocalDate start,LocalDate end) throws TransactionalException;
	Transaction getById(Long txnId);
	Transaction addTxn(Transaction txn) throws TransactionalException;
	Transaction updateTxn(Transaction txn) throws TransactionalException;
	Transaction deleteTxn(Long txnId) throws TransactionalException;
}
