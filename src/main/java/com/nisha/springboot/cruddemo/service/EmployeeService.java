package com.nisha.springboot.cruddemo.service;

import java.util.List;

import com.nisha.springboot.cruddemo.entity.Employee;

public interface EmployeeService {
	public List<Employee> findAll();
	public Employee findById(int theId);
	public void save(Employee employee);
	public void deleteById(int theId);
}
