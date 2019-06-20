package com.nisha.springboot.cruddemo.dao;

import java.util.List;
import com.nisha.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {
	public List<Employee> findAll();
	public Employee findById(int id);
	public void save(Employee employee);
	public void deleteById(int theId);
}
