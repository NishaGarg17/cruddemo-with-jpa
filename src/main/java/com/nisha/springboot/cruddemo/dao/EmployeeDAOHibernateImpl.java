package com.nisha.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nisha.springboot.cruddemo.entity.Employee;


@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define fields for EntityManager
	private EntityManager entityManager;
	
	// set up Constructor Injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	@Override
	public List<Employee> findAll() {
				// get the current Hibernate Session
				Session currentSession = entityManager.unwrap(Session.class);
				// create a query
				Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
				
				// execute a query and get the result list
				List<Employee> employeeList = theQuery.getResultList();
				
				// return the result list
				return employeeList;
	}

	@Override
	public Employee findById(int id) {
		// get the current Hibernate Session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query to get an employee based on Id
		Query<Employee> theQuery = currentSession.createQuery("from Employee e where e.id =: id" , Employee.class);
		theQuery.setParameter("id", id);
		Employee employee = theQuery.getSingleResult();
		return employee;
	}
	
	@Override
	public void save(Employee employee) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Save an Employee
		currentSession.saveOrUpdate(employee);
		
	}

	@Override
	public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Employee> theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}


}
