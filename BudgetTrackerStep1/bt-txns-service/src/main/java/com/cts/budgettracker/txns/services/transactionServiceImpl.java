package com.cts.budgettracker.txns.services;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.budgettracker.txns.entities.AccountHolder;
import com.cts.budgettracker.txns.entities.Transaction;
import com.cts.budgettracker.txns.entities.TransactionType;
import com.cts.budgettracker.txns.exceptions.TxnsException;
import com.cts.budgettracker.txns.repos.AccountHolderRepo;
import com.cts.budgettracker.txns.repos.TransactionRepo;

@Service
public class transactionServiceImpl implements TransactionService {

	@Autowired
	private AccountHolderRepo ahRepo;
	
	@Autowired
	private TransactionRepo txnRepo;

	@Autowired
	private ProfileServiceClient profileService;
	
	@Override
	public List<Transaction> getAllbyAccountHolderId(Long ahId) throws TxnsException {
		return txnRepo.findAllByAccountHolderId(ahId);
	}

	@Override
	public List<Transaction> getAllbyAccountHolderIdOfPeiod(Long ahId, LocalDate start, LocalDate end)
			throws TxnsException {
		return txnRepo.findAllByAccountHolderIdOfPeriod(ahId, start, end);
	}

	@Override
	public Transaction getById(Long txnId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction addTxn(Transaction txn) throws TxnsException {
		System.out.println(txn);
		long ahId = txn.getHolder().getAhId();
		AccountHolder ah = ahRepo.findById(ahId).orElse(null);
		
		if(ah==null) {
			//if account holder does not exist on txns-service database
			//check if its exists on profile-service database
			if(!profileService.existsProfileByAccountHolderId(ahId)) {
				throw new TxnsException("Can not attach a transaction to a non-existing AccountHolder");
			}
			
			ah = new AccountHolder(ahId, 0.0, new TreeSet<>());
		}
		
		txn.setHolder(ah);
		ah.setCurrentBalance(txn.getType()==TransactionType.CREDIT?
				ah.getCurrentBalance()+txn.getAmount():
					ah.getCurrentBalance()-txn.getAmount());
		ahRepo.save(ah);
		txn = txnRepo.save(txn);
		return txn;
	}

	@Override
	public Transaction updateTxn(Transaction txn) throws TxnsException {
		long ahId = txn.getHolder().getAhId();
		AccountHolder ah = ahRepo.findById(ahId).orElse(null);
		txn.setHolder(ah);
		ah.setCurrentBalance(txn.getType()==TransactionType.CREDIT?
				ah.getCurrentBalance()+txn.getAmount():
					ah.getCurrentBalance()-txn.getAmount());
		ahRepo.save(ah);
		txn = txnRepo.save(txn);
		return txn;
	}

	@Override
	public void deleteTxn(Long txnId) throws TxnsException {
		txnRepo.deleteById(txnId);
	}

}
