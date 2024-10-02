package com.efrp.LoanApplicationSubmission.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.efrp.LoanApplicationSubmission.dto.ApiResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<String> anyRuntimeException(RuntimeException e) {
		
		return new ResponseEntity<String>("There was some error at the server: /n" + e.getMessage(),HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
