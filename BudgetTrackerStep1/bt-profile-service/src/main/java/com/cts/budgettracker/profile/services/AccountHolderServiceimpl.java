package com.cts.budgettracker.profile.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.budgettracker.profile.entities.AccountHolder;
import com.cts.budgettracker.profile.exception.ProfileException;
import com.cts.budgettracker.profile.repos.AccountHolderRepo;

@Service
public class AccountHolderServiceimpl implements AccountHolderService {

	@Autowired
	private AccountHolderRepo ahRepo;
	
	@Override
	public List<AccountHolder> getAll() throws ProfileException {
		return ahRepo.findAll();
	}

	@Override
	public AccountHolder getById(Long ahId) throws ProfileException {
		return ahRepo.findById(ahId).orElse(null);
	}

	@Override
	public AccountHolder add(AccountHolder accountHolder) throws ProfileException {
		if(accountHolder.getAhId()!=null && ahRepo.existsById(accountHolder.getAhId())) {
			throw new ProfileException("An account holder with the provided id exists already");
		}
		if(ahRepo.existsByEmailId(accountHolder.getEmailId())) {
			throw new ProfileException("An account holder with the provided emailid exists already");
		}
		if(ahRepo.existsByMobileNumber(accountHolder.getMobileNumber())) {
			throw new ProfileException("An account holder with the provided mobile number exists already");
		}
		
		return ahRepo.save(accountHolder);
	}

	@Override
	public AccountHolder update(AccountHolder accountHolder) throws ProfileException {
		
		AccountHolder oldAccountHolder = ahRepo.findById(accountHolder.getAhId()).orElse(null);
		
		if(oldAccountHolder==null) {
			throw new ProfileException("An account holder with the provided id not found");
		}
		
		if(!oldAccountHolder.getEmailId().equals(accountHolder.getEmailId()) && 
				ahRepo.existsByEmailId(accountHolder.getEmailId())) {
			throw new ProfileException("An account holder with the provided emailid exists already");
		}
		
		if(!oldAccountHolder.getMobileNumber().equals(accountHolder.getMobileNumber()) &&
				ahRepo.existsByMobileNumber(accountHolder.getMobileNumber())) {
			throw new ProfileException("An account holder with the provided mobile number exists already");
		}
		
		return ahRepo.save(accountHolder);
	}

}
