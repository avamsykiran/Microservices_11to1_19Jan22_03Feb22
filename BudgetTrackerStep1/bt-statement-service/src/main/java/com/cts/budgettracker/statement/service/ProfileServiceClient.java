package com.cts.budgettracker.statement.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.budgettracker.statement.model.AccountHolderProfileServiceBoundedContext;

@FeignClient(name="profile-service",url="http://localhost:9100")
public interface ProfileServiceClient {

	@GetMapping("/profiles/{ahId}")
	AccountHolderProfileServiceBoundedContext getAccountHolderById(@PathVariable("ahId") long ahId);
}
