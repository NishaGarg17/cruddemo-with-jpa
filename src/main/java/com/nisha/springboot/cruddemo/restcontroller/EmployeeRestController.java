package com.nisha.springboot.cruddemo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisha.springboot.cruddemo.entity.Employee;
import com.nisha.springboot.cruddemo.service.EmployeeService;

@RequestMapping("/api")
@RestController
public class EmployeeRestController {
	// inject Employee Service
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService= employeeService;
	}
	
	// expose "/employees" end point and return the list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	// expose "/employee" end point and return employee based on id
	@GetMapping("/employee/{employeeId}")
	public Employee find(@PathVariable int employeeId) {
		return employeeService.findById(employeeId);
	}
	
	// expose "/employees" end point to add a new employee
	@PostMapping("/employees")
	public Employee save(@RequestBody Employee employee) {
		// just in case they pass an id in JSON... set id to 0
		// this is to force a save of new item... instead of update
		 employee.setId(0);
		 employeeService.save(employee);
		 return employee;
	}
	
	// expose "/employees" end point to update an existing employee based on Id
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee employee) {
		employeeService.save(employee);
		return employee;
	}
	
	// expose "/employees" end point to delete an existing employee based on Id
		@DeleteMapping("/employees/{employeeId}")
		public String delete(@PathVariable int employeeId) {
			if(employeeService.findById(employeeId) == null) {
				throw new RuntimeException("Employee Id not found- " + employeeId);
			}
			employeeService.deleteById(employeeId);
			return "Employee having id: " + employeeId + " deleted successfully";
		}
}
