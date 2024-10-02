package com.efrp.LoanApplicationSubmission.model.dto;

import java.util.List;

import com.efrp.LoanApplicationSubmission.entity.LoanApplication;
import com.efrp.LoanApplicationSubmission.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanResponseDto {

	private User user;
	private List<LoanApplication> loanApplicationList;
	
	
}
