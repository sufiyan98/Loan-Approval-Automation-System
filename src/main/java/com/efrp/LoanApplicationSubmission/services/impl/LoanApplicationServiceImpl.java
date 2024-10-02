package com.efrp.LoanApplicationSubmission.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.efrp.LoanApplicationSubmission.entity.LoanApplication;
import com.efrp.LoanApplicationSubmission.exceptions.ResourceNotFoundException;
import com.efrp.LoanApplicationSubmission.model.User;
import com.efrp.LoanApplicationSubmission.model.dto.LoanResponseDto;
import com.efrp.LoanApplicationSubmission.repositories.LoanApplicationRepository;
import com.efrp.LoanApplicationSubmission.services.LoanApplicationService;
import com.efrp.LoanApplicationSubmission.services.external.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService{

	private LoanApplicationRepository loanApplicationRepository;
	
	private UserService userService;

	public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository, UserService userService) {
		this.loanApplicationRepository = loanApplicationRepository;
		this.userService = userService;
	}

	public LoanApplication saveLoanApplication(LoanApplication loanApplication) {
        return loanApplicationRepository.save(loanApplication);
    }
    
    public List<LoanApplication> findAllByUserId(int userId){
    	return this.loanApplicationRepository.findByUserId(userId);
    }

    public LoanApplication editLoanApplication(LoanApplication loanApplication) {
        findById(loanApplication.getLoanId());

        return loanApplicationRepository.save(loanApplication);
    }

    public void deleteLoanApplication(int id) {
        LoanApplication existingApplication = findById(id);

        loanApplicationRepository.delete(existingApplication);
    }

    public LoanApplication findById(int id) {
        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepository.findById(id);
        return optionalLoanApplication.orElseThrow(() -> new IllegalArgumentException("Loan application not found with id: " + id));
    }

	@Override
	public LoanResponseDto fetchAllByUserId(int userId) {
		
		User user = this.userService.getUser(userId);
		log.info("User details: " + user);
		List<LoanApplication> loanApplicationList = this.findAllByUserId(userId);
		log.info("Loan application list :" + loanApplicationList);
		LoanResponseDto dto = new LoanResponseDto();
		dto.setUser(user);
		dto.setLoanApplicationList(loanApplicationList);
		
		return dto;
	}

	
	@Override
	public LoanApplication updateCaStatus(int loanId) {
		LoanApplication loan = this.loanApplicationRepository.findById(loanId).get();
		loan.setCaStatus(true);
		LoanApplication updatedLoan = this.loanApplicationRepository.save(loan);
		return updatedLoan;
	}

	@Override
	public LoanApplication getLoanDetails(int loanId, int userId) {

		LoanApplication receivedDetails = this.loanApplicationRepository.findByLoanIdAndUserId(loanId, userId);

		return receivedDetails;
	}

	@Override
	public List<LoanApplication> getAllLoanDetails() {

		List<LoanApplication> allLoanDetails = this.loanApplicationRepository.findByDeStatusTrue();

		return allLoanDetails;

	}

	@Override
	public void approve(int loanId, int userId) {

	
		LoanApplication loan = this.loanApplicationRepository.findByLoanIdAndUserId(loanId, userId);

		if(loan != null) {
			loan.setDeStatus(true);

			this.loanApplicationRepository.save(loan);
		} else {
			throw new ResourceNotFoundException("Loan","LoanId","UserId",loanId,userId);
		}
		

	}

	@Override
	public void reject(int loanId, int userId) {

		LoanApplication loan = this.loanApplicationRepository.findByLoanIdAndUserId(loanId, userId);

		if(loan != null) {
			loan.setDeStatus(false);

			this.loanApplicationRepository.save(loan);
		} else {
			throw new ResourceNotFoundException("Loan","LoanId","UserId",loanId,userId);
		}
		
	}

}
