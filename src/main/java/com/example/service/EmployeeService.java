package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDAO employeedao;
	public  Employee findEmployeeById(Long empid) {
		if (empid==2) {
			throw new IllegalArgumentException("Your account is blocked");
		}
		
		return employeedao.findOne(empid);
	}
	
	

}
