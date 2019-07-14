package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.Employeerepository;

@Repository
public class EmployeeDAO {
	
	@Autowired
	Employeerepository employeerepository;
	
	/*save employee*/
	public Employee save(Employee emp) {
		
		
		return employeerepository.save(emp);
	}
	
	/*search employee*/
	
	public List<Employee> findAll(){
		return employeerepository.findAll();
	}
	/*get employee*/
	
	public Employee findOne(Long empid) {
		return employeerepository.getOne(empid);

		
	}
	/*delete an employee*/
	
	public void delete(Employee emp) {
		employeerepository.delete(emp);
	}
	
	
}
