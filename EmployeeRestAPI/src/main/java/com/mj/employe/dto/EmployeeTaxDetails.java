package com.mj.employe.dto;

import java.math.BigDecimal;

public class EmployeeTaxDetails {
	private Long employeeId;
    private String firstName;
    private String lastName;
    private BigDecimal yearlySalary;
    private BigDecimal taxAmount;
    private BigDecimal cessAmount;
    
	public EmployeeTaxDetails() {
		
	}
	public EmployeeTaxDetails(Long employeeId, String firstName, String lastName, BigDecimal yearlySalary,
			BigDecimal taxAmount, BigDecimal cessAmount) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearlySalary = yearlySalary;
		this.taxAmount = taxAmount;
		this.cessAmount = cessAmount;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public BigDecimal getYearlySalary() {
		return yearlySalary;
	}
	public void setYearlySalary(BigDecimal yearlySalary) {
		this.yearlySalary = yearlySalary;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getCessAmount() {
		return cessAmount;
	}
	public void setCessAmount(BigDecimal cessAmount) {
		this.cessAmount = cessAmount;
	}
    
}
