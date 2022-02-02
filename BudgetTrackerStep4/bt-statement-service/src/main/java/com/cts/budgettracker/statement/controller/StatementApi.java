package com.cts.budgettracker.statement.controller;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.budgettracker.statement.exception.StatementException;
import com.cts.budgettracker.statement.model.Statement;
import com.cts.budgettracker.statement.service.StatementService;

@RestController
@RequestMapping("/statement")
public class StatementApi {

	@Autowired
	private StatementService service;
	
	@GetMapping("/{ahId}/{year}")
	private Statement getAnnualStatement(
			@PathVariable("ahId") long ahId,
			@PathVariable("year") int year) throws StatementException {
		return service.getStatement(
				ahId, 
				LocalDate.of(year, Month.JANUARY, 1), 
				LocalDate.of(year, Month.DECEMBER, 31));
	}
	
	@GetMapping("/{ahId}/{year}/{month}")
	private Statement getMonthlyStatement(
			@PathVariable("ahId") long ahId,
			@PathVariable("year") int year,
			@PathVariable("month") Month month) throws StatementException {
		
		YearMonth yrMonth = YearMonth.of(year, month);
		
		return service.getStatement(ahId,yrMonth.atDay(1),yrMonth.atEndOfMonth());
	}
}
