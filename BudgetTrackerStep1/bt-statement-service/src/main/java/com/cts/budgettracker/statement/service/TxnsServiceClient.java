package com.cts.budgettracker.statement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.budgettracker.statement.model.AccountHolderTxnServiceBoundedContext;
import com.cts.budgettracker.statement.model.Transaction;

@FeignClient(name="txns-service",url="http://localhost:9200")
public interface TxnsServiceClient {

	@GetMapping("txns/holder/{ahId}")
	AccountHolderTxnServiceBoundedContext getAccountHolder(@PathVariable("ahId") long ahId);
	
	@GetMapping("txns/holder/{ahId}/{start}/{end}")
	List<Transaction> getTransactions(
			@PathVariable("ahId") long ahId,
			@PathVariable("start") LocalDate start,
			@PathVariable("end") LocalDate end);
}
