package com.mj.employee.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.employe.dto.EmployeeTaxDetails;
import com.mj.employee.entity.Employee;
import com.mj.employee.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // Perform additional validation and business logic if required
        return employeeRepository.save(employee);
    }
	@Override
	public List<EmployeeTaxDetails> calculateTaxDeductionForCurrentFinancialYear() {
	    // Retrieve all employees from the database
	    List<Employee> employees = employeeRepository.findAll();

	    List<EmployeeTaxDetails> taxDetailsList = new ArrayList<>();

	    // Calculate tax details for each employee
	    for (Employee employee : employees) {
	        BigDecimal yearlySalary = calculateYearlySalary(employee);
	        BigDecimal taxAmount = calculateTaxAmount(yearlySalary);
	        BigDecimal cessAmount = calculateCessAmount(yearlySalary);

	        EmployeeTaxDetails taxDetails = new EmployeeTaxDetails();
	        taxDetails.setEmployeeId(employee.getEmployeeId());
	        taxDetails.setFirstName(employee.getFirstName());
	        taxDetails.setLastName(employee.getLastName());
	        taxDetails.setYearlySalary(yearlySalary);
	        taxDetails.setTaxAmount(taxAmount);
	        taxDetails.setCessAmount(cessAmount);

	        taxDetailsList.add(taxDetails);
	    }

	    return taxDetailsList;
	}

	private BigDecimal calculateYearlySalary(Employee employee) {
	    // Calculate the number of months since the employee's DOJ
	    LocalDate now = LocalDate.now();
	    Period period = Period.between(employee.getDoj(), now);
	    int monthsWorked = (period.getYears() * 12) + period.getMonths();

	    // Calculate the number of months to consider for salary calculation
	    int monthsToConsider = monthsWorked - (employee.getDoj().getDayOfMonth() > 15 ? 1 : 0);

	    // Calculate total salary considering the number of months
	    return employee.getSalary().multiply(BigDecimal.valueOf(monthsToConsider));
	}

	private BigDecimal calculateTaxAmount(BigDecimal yearlySalary) {
	    if (yearlySalary.compareTo(BigDecimal.valueOf(250000)) <= 0) {
	        return BigDecimal.ZERO;
	    } else if (yearlySalary.compareTo(BigDecimal.valueOf(500000)) <= 0) {
	        return yearlySalary.multiply(BigDecimal.valueOf(0.05));
	    } else if (yearlySalary.compareTo(BigDecimal.valueOf(1000000)) <= 0) {
	        BigDecimal remainingSalary = yearlySalary.subtract(BigDecimal.valueOf(500000));
	        return BigDecimal.valueOf(25000).add(remainingSalary.multiply(BigDecimal.valueOf(0.1)));
	    } else {
	        BigDecimal remainingSalary = yearlySalary.subtract(BigDecimal.valueOf(1000000));
	        return BigDecimal.valueOf(125000).add(remainingSalary.multiply(BigDecimal.valueOf(0.2)));
	    }
	}

	private BigDecimal calculateCessAmount(BigDecimal yearlySalary) {
	    BigDecimal excessAmount = yearlySalary.subtract(BigDecimal.valueOf(2500000));
	    if (excessAmount.compareTo(BigDecimal.ZERO) > 0) {
	        return excessAmount.multiply(BigDecimal.valueOf(0.02));
	    } else {
	        return BigDecimal.ZERO;
	    }
	}

}
