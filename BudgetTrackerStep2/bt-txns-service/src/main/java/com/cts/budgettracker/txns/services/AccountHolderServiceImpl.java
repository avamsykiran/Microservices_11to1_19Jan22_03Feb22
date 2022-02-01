package com.cts.budgettracker.txns.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.budgettracker.txns.entities.AccountHolder;
import com.cts.budgettracker.txns.repos.AccountHolderRepo;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	@Autowired
	private AccountHolderRepo ahRepo;
	
	@Override
	public AccountHolder getById(Long ahId) {
		return ahRepo.findById(ahId).orElse(null);
	}

}
