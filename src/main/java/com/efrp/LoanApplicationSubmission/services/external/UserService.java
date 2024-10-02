package com.efrp.LoanApplicationSubmission.services.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.efrp.LoanApplicationSubmission.model.User;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

	@GetMapping("/users/custom/{userId}")
	User getUser(@PathVariable("userId") int userId);
}
