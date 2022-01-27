package com.cts.budgettracker.profile.services;

import java.util.List;

import com.cts.budgettracker.profile.entities.AccountHolder;
import com.cts.budgettracker.profile.exception.ProfileException;

public interface AccountHolderService {
	
	List<AccountHolder> getAll() throws ProfileException;
	AccountHolder getById(Long ahId) throws ProfileException;
	boolean existsById(Long ahId) throws ProfileException;
	AccountHolder add(AccountHolder accountHolder) throws ProfileException;
	AccountHolder update(AccountHolder accountHolder) throws ProfileException;
}
