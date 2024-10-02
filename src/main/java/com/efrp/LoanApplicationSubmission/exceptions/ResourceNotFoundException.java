package com.efrp.LoanApplicationSubmission.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	final String resourceName;
	final String fieldName;
	final long fieldValue;

	String secondaryFieldName;
	long secondaryFieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String secondaryFieldName, long fieldValue,
			long secondaryFieldValue) {
		super(String.format("%s not found with %s , %s : %s, %s", resourceName, fieldName, secondaryFieldName,
				fieldValue, secondaryFieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.secondaryFieldName = secondaryFieldName;
		this.secondaryFieldValue = secondaryFieldValue;
	}

}
