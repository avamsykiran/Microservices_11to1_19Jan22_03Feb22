package com.cts.budgettracker.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.budgettracker.profile.entities.AccountHolder;
import com.cts.budgettracker.profile.exception.ProfileException;
import com.cts.budgettracker.profile.services.AccountHolderService;

@RestController
@RequestMapping("/profiles")
public class AccountHolderApi {

	@Autowired
	private AccountHolderService ahService;
	
	@GetMapping
	public ResponseEntity<List<AccountHolder>> getAllHandler() throws ProfileException{
		return ResponseEntity.ok(ahService.getAll());
	}
	
	@GetMapping("/{ahId}")
	public ResponseEntity<AccountHolder> getByIdHandler(@PathVariable("ahId") Long ahId) throws ProfileException{
		AccountHolder ah = ahService.getById(ahId);
		return ah==null? ResponseEntity.notFound().build() : ResponseEntity.ok(ah);
	}
	
	@GetMapping("/{ahId}/exists")
	public ResponseEntity<Boolean> existsByIdHandler(@PathVariable("ahId") Long ahId) throws ProfileException{
		return ResponseEntity.ok(ahService.existsById(ahId));
	}
	
	@PostMapping
	public ResponseEntity<AccountHolder> createRecord(@RequestBody AccountHolder ach) throws ProfileException {
		ach=ahService.add(ach);
		return new ResponseEntity<AccountHolder>(ach, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<AccountHolder> updateRecord(@RequestBody AccountHolder ach) throws ProfileException {
		ach=ahService.update(ach);
		return new ResponseEntity<AccountHolder>(ach, HttpStatus.ACCEPTED);
	}
}
