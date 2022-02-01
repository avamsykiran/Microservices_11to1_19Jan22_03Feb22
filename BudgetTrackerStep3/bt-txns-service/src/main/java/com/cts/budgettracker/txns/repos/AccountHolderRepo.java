package com.cts.budgettracker.txns.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.budgettracker.txns.entities.AccountHolder;

public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {

}
