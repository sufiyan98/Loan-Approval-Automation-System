package com.efrp.LoanApplicationSubmission.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

	private String userId;
	private String name;
	private String email;
	private String about;
}
