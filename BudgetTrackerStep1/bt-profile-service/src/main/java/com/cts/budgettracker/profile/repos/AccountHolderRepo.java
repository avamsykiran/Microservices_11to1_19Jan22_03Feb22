package com.cts.budgettracker.profile.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.budgettracker.profile.entities.AccountHolder;

@Repository
public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long>{
	boolean existsByEmailId(String emailId);
	boolean existsByMobileNumber(String mobielNumber);
}
