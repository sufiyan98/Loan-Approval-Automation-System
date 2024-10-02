package com.efrp.LoanApplicationSubmission.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efrp.LoanApplicationSubmission.entity.LoanApplication;
import com.efrp.LoanApplicationSubmission.model.LoanApplicationCreatedEvent;
import com.efrp.LoanApplicationSubmission.model.dto.LoanResponseDto;
import com.efrp.LoanApplicationSubmission.services.KafkaService;
import com.efrp.LoanApplicationSubmission.services.LoanApplicationService;

@RestController
@RequestMapping("/api/loan/application")
public class LoanApplicationController {
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private KafkaService kafkaService;
	
	@PostMapping
	public ResponseEntity<String> submitApplication(@RequestBody LoanApplication loanApplication){
		
		LoanApplication submittedLoanApplication;
		
		try {
			
		submittedLoanApplication = loanApplicationService.saveLoanApplication(loanApplication);
		
		//sending message to kafka producer
		
		LoanApplicationCreatedEvent event = new LoanApplicationCreatedEvent();
		event.setApplicationId(submittedLoanApplication.getLoanId());
		event.setUserId(submittedLoanApplication.getUserId());
		
		
		kafkaService.publish("loan-application-created-topic",event);
		
		return ResponseEntity.ok("Loan Application submitted successfuly with loan id: " + submittedLoanApplication.getLoanId());
		
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity<String> updateApplication(@RequestBody LoanApplication loanApplication){
		try {
			loanApplicationService.editLoanApplication(loanApplication);
			return ResponseEntity.ok("Update application successfuly");
		}catch(Exception e) {
			throw new RuntimeException("Update failed: " + e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LoanApplication> fetchLoanApplication(@PathVariable("id") int id){
		LoanApplication loanApplication;
		try {
			loanApplication = loanApplicationService.findById(id);
			return ResponseEntity.ok(loanApplication);
		}catch(Exception e) {
			throw new RuntimeException("Fetching loan application failed: " + e.getLocalizedMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLoanApplication(@PathVariable("id") int id){
		try {
			loanApplicationService.deleteLoanApplication(id);
			return ResponseEntity.ok("Deleted loan application successfully");
		}catch(Exception e) {
			throw new RuntimeException("Error in deleting loan Application: "+ e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<LoanResponseDto> fetchAllByUserId(@PathVariable("id") int userId){
		LoanResponseDto responseDto = null;
		try {
			responseDto = this.loanApplicationService.fetchAllByUserId(userId);
		}catch(Exception e) {
			throw new RuntimeException("Error while fetching loan details");
		}
		
		return ResponseEntity.ok(responseDto);
		 
	}
	
	@GetMapping("/loan/{loanId}/user/{userId}")
	public ResponseEntity<LoanApplication> getDetails(@PathVariable int loanId, @PathVariable int userId) {

		LoanApplication receivedDetails = this.loanApplicationService.getLoanDetails(loanId, userId);

		return new ResponseEntity<>(receivedDetails, HttpStatus.OK);
	}

	@PutMapping("/{loanId}")
	public ResponseEntity<LoanApplication> updateCaDetails(@PathVariable int loanId) {

		LoanApplication recivedCaDetails = this.loanApplicationService.updateCaStatus(loanId);

		return new ResponseEntity<>(recivedCaDetails, HttpStatus.OK);
	}

	@GetMapping("/getallDetails")
	public ResponseEntity<List<LoanApplication>> getAllloanDetails() {

		List<LoanApplication> recivedAllDetails = this.loanApplicationService.getAllLoanDetails();

		return ResponseEntity.ok(recivedAllDetails);

	}

	@PutMapping("/approve/loan/{loanId}/user/{userId}")
	public void approveDeStatus(@PathVariable int loanId, @PathVariable int userId) {

		this.loanApplicationService.approve(loanId, userId);

	}

	@PutMapping("/reject/loan/{loanId}/user/{userId}")
	public void rejectDeStatus(@PathVariable int loanId, @PathVariable int userId) {

		this.loanApplicationService.reject(loanId, userId);

	}

}
