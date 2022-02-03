package com.cts.budgettracker.profile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.budgettracker.profile.exception.ProfileException;

@RestControllerAdvice
public class GlobalExceptionAdvicer {

	@ExceptionHandler(ProfileException.class)
	public ResponseEntity<String> handleUserDefiendException(ProfileException exp){
		return new ResponseEntity<String>(exp.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleSystemDefiendException(Exception exp){
		exp.printStackTrace();
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
