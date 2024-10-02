package com.efrp.LoanApplicationSubmission.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efrp.LoanApplicationSubmission.entity.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Integer>{

	List<LoanApplication> findByUserId(int userId);
	
	LoanApplication findByLoanIdAndUserId(int loanId, int userId);
	
	List<LoanApplication> findByDeStatusTrue();
	
}
