package com.cts.budgettracker.txns.services;

import java.time.LocalDate;
import java.util.List;


import com.cts.budgettracker.txns.entities.Transaction;
import com.cts.budgettracker.txns.exceptions.TxnsException;

public interface TransactionService {
	List<Transaction> getAllbyAccountHolderId(Long ahId) throws TxnsException;
	List<Transaction> getAllbyAccountHolderIdOfPeiod(Long ahId,LocalDate start,LocalDate end) throws TxnsException;
	Transaction getById(Long txnId);
	Transaction addTxn(Transaction txn) throws TxnsException;
	Transaction updateTxn(Transaction txn) throws TxnsException;
	void deleteTxn(Long txnId) throws TxnsException;
}
