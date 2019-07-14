package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/company")
public class EmployeeController {

	@GetMapping("/hello")
	public String helloEmployeeController() {
		return "Hello,Employee service is up and running";
	}

	@Autowired(required = true)
	EmployeeDAO employeeDAO;

	@Autowired
	EmployeeService employeeservice;

	/* save employee */
	@PostMapping("/employee/create")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		try {
			if (emp.getName().equals("Subodh")) {
				System.out.println("entered subodh ");
				throw new IllegalArgumentException();
			}
			return employeeDAO.save(emp);
		} catch (IllegalArgumentException e) {
			System.out.println("entered Controller IllegalArgumentException ");
			throw new IllegalArgumentException();
		}

	}

	/* get employee */
	@GetMapping("/employee")
	public List<Employee> getAllEmployees() {
		return employeeDAO.findAll();
	}

	/* search employee */
	@GetMapping("/search/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long empid) {

		Employee emp = employeeservice.findEmployeeById(empid);

		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}

	/* update employee */
	@PutMapping("/employee/update/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable(value = "id") Long empid,
			@Valid @RequestBody Employee empdetails) {
		Employee emp = employeeDAO.findOne(empid);
		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		emp.setName(empdetails.getName());
		emp.setDesignition(empdetails.getDesignition());
		emp.setExpertise(empdetails.getExpertise());
		Employee updateEmployeeById = employeeDAO.save(emp);
		return ResponseEntity.ok().body(updateEmployeeById);

	}

	/* delete employee */
	@DeleteMapping("/employee/delete/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long empid) {
		
		Employee emp = employeeDAO.findOne(empid);
		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		employeeDAO.delete(emp);
		return ResponseEntity.ok().build();
	}

}
