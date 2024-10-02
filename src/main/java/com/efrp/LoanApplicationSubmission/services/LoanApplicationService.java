package com.efrp.LoanApplicationSubmission.services;

import java.util.List;

import com.efrp.LoanApplicationSubmission.entity.LoanApplication;
import com.efrp.LoanApplicationSubmission.model.dto.LoanResponseDto;

public interface LoanApplicationService {

	LoanApplication saveLoanApplication(LoanApplication loanApplication);

    LoanApplication editLoanApplication(LoanApplication loanApplication);

    void deleteLoanApplication(int id);

    LoanApplication findById(int id);

	LoanResponseDto fetchAllByUserId(int userId);
	
	LoanApplication getLoanDetails(int loanId, int userId);

	LoanApplication updateCaStatus(int loanId);

	List<LoanApplication> getAllLoanDetails();
	
	void approve(int loanId, int userId);
	
	void reject(int loanId, int userId);
}
