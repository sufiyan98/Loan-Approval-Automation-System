package com.efrp.LoanApplicationSubmission.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
	
	private int userId;

	private double creditScore;

	private int age;

	private String empType;

	private double yearOfExp;

	private double income;

	private double loanAmount;

	private int loanTerm;

	private String typeOfLoan;

	private String puropseOfloan;

	private double emi;

	private double loanAmountToIncome;

	private String collateral;

	private String doc;

	private String citizenShip;

	private double rateOfInterest;

	private boolean caStatus;

	private boolean deStatus;

}
