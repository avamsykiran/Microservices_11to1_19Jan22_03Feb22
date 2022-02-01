package com.cts.budgettracker.statement.service;

import java.time.LocalDate;

import com.cts.budgettracker.statement.exception.StatementException;
import com.cts.budgettracker.statement.model.Statement;

public interface StatementService {

	Statement getStatement(long ahId,LocalDate from,LocalDate to) throws StatementException;
}
