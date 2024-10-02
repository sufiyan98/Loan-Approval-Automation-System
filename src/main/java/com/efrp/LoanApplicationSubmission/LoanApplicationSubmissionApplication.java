package com.efrp.LoanApplicationSubmission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LoanApplicationSubmissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplicationSubmissionApplication.class, args);
	}

}
