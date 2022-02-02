package com.cts.budgettracker.txns.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="profile-service")
public interface ProfileServiceClient {

	@GetMapping("/profiles/{ahId}/exists")
	boolean existsProfileByAccountHolderId(@PathVariable("ahId") long ahId);
}
