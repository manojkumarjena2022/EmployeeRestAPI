package com.mj.employee.service;

import java.util.List;

import com.mj.employe.dto.EmployeeTaxDetails;
import com.mj.employee.entity.Employee;

public interface EmployeeService {
	Employee createEmployee(Employee employee);

	List<EmployeeTaxDetails> calculateTaxDeductionForCurrentFinancialYear();
	

}
