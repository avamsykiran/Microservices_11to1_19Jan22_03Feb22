package com.cts.budgettracker.statement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.budgettracker.statement.exception.StatementException;

@RestControllerAdvice
public class GlobalExceptionAdvicer {

	@ExceptionHandler(StatementException.class)
	public ResponseEntity<String> handleUserDefiendException(StatementException exp){
		return new ResponseEntity<String>(exp.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleSystemDefiendException(Exception exp){
		exp.printStackTrace();
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
