package com.mj.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.employe.dto.EmployeeTaxDetails;
import com.mj.employee.entity.Employee;
import com.mj.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> createEmployee(@Validated @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    @GetMapping("/tax-deduction")
    public ResponseEntity<List<EmployeeTaxDetails>> getTaxDeductionForCurrentFinancialYear() {
        List<EmployeeTaxDetails> taxDetailsList = employeeService.calculateTaxDeductionForCurrentFinancialYear();
        return new ResponseEntity<>(taxDetailsList, HttpStatus.OK);
    }

}