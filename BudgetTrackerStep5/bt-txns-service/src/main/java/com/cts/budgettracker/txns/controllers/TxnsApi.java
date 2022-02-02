package com.cts.budgettracker.txns.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.budgettracker.txns.entities.AccountHolder;
import com.cts.budgettracker.txns.entities.Transaction;
import com.cts.budgettracker.txns.exceptions.TxnsException;
import com.cts.budgettracker.txns.services.AccountHolderService;
import com.cts.budgettracker.txns.services.TransactionService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/txns")
public class TxnsApi {

	@Autowired
	private TransactionService txnService;
	
	@Autowired
	private AccountHolderService ahService;
	
	@GetMapping("/holder/{ahId}")
	public ResponseEntity<AccountHolder> getAccountHolder(@PathVariable("ahId")Long ahId) {
		AccountHolder ah = ahService.getById(ahId);
		return new ResponseEntity<AccountHolder>(ah, ah!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}	
	
	@GetMapping("/holder/{ahId}/{start}/{end}")
	public List<Transaction> getAllByAccountHolder(
			@PathVariable("ahId")Long ahId,
			@PathVariable("start") @DateTimeFormat(iso=ISO.DATE) LocalDate start,
			@PathVariable("end") @DateTimeFormat(iso=ISO.DATE) LocalDate end) throws TxnsException{
		return txnService.getAllbyAccountHolderIdOfPeiod(ahId, start, end);
	}
	
	@GetMapping("/{txnId}")
	public ResponseEntity<Transaction> getById(@PathVariable("txnId") Long txnId) throws TxnsException{
		Transaction txn = txnService.getById(txnId);
		//return txn==null? ResponseEntity.notFound().build():ResponseEntity.ok(txn);
		return new ResponseEntity<Transaction>(txn, txn==null?HttpStatus.NOT_FOUND:HttpStatus.OK);
	}
	
	@PostMapping
	@CircuitBreaker(name="addTxn",fallbackMethod = "addTxnFallBack")
	public ResponseEntity<Transaction> addTxn(@RequestBody Transaction txn) throws TxnsException {
		return new ResponseEntity<>(txnService.addTxn(txn),HttpStatus.CREATED);
	}
	
	public ResponseEntity<Transaction> addTxnFallBack(Transaction txn,Throwable exp) throws TxnsException {
		//log exp
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@PutMapping
	public ResponseEntity<Transaction> updateTxn(@RequestBody Transaction txn) throws TxnsException {
		return new ResponseEntity<>(txnService.updateTxn(txn),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{txnId}")
	public ResponseEntity<Void> deleteById(@PathVariable("txnId") Long txnId) throws TxnsException{
		txnService.deleteTxn(txnId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
