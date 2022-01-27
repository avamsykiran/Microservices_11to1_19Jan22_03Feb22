package com.cts.budgettracker.txns.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.TransactionalException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.budgettracker.txns.entities.AccountHolder;
import com.cts.budgettracker.txns.entities.Transaction;
import com.cts.budgettracker.txns.repos.AccountHolderRepo;
import com.cts.budgettracker.txns.repos.TransactionRepo;

@Service
public class transactionServiceImpl implements TransactionService {

	@Autowired
	private AccountHolderRepo ahRepo;
	
	@Autowired
	private TransactionRepo txnRepo;
	
	@Override
	public List<Transaction> getAllbyAccountHolderId(Long ahId) throws TransactionalException {
		return txnRepo.findAllByAccountHolderId(ahId);
	}

	@Override
	public List<Transaction> getAllbyAccountHolderIdOfPeiod(Long ahId, LocalDate start, LocalDate end)
			throws TransactionalException {
		return txnRepo.findAllByAccountHolderIdOfPeriod(ahId, start, end);
	}

	@Override
	public Transaction getById(Long txnId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction addTxn(Transaction txn) throws TransactionalException {
		AccountHolder ah = ahRepo.findById(txn.getHolder().getAhId()).orElse(null);
		
		if(ah==null) {
			//if account holder does not exist on txns-service database
			//check if its exists on profile-service database
		}
		
		return txn;
	}

	@Override
	public Transaction updateTxn(Transaction txn) throws TransactionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction deleteTxn(Long txnId) throws TransactionalException {
		// TODO Auto-generated method stub
		return null;
	}

}
