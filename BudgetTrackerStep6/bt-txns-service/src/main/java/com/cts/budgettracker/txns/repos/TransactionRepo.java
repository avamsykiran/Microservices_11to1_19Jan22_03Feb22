package com.cts.budgettracker.txns.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.budgettracker.txns.entities.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long>{
	
	@Query("SELECT t FROM Transaction t WHERE t.holder.ahId=:ahId")
	List<Transaction> findAllByAccountHolderId(Long ahId);
	
	@Query("SELECT t FROM Transaction t WHERE t.holder.ahId=:ahId AND t.dateOfTransaction BETWEEN :start AND :end")
	List<Transaction> findAllByAccountHolderIdOfPeriod(Long ahId,LocalDate start,LocalDate end);

}
