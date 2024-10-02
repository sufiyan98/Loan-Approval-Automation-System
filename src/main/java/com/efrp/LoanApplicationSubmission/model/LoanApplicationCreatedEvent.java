package com.efrp.LoanApplicationSubmission.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanApplicationCreatedEvent {

	private int applicationId;
	private int userId;
}
