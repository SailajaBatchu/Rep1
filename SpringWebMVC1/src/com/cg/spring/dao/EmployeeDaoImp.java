package com.cg.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.spring.dto.Employee;

@Repository("employeedao") //makes the class as a repository
public class EmployeeDaoImp implements EmployeeDao {

	@Autowired  //to inject bean
	
	@PersistenceContext // to make database to particular operation
	EntityManager entityManager;
	
	@Override
	public void addEmployee(Employee e) { //add to database
		entityManager.persist(e); //persist means save
		
	}

	@Override
	public List<Employee> showEmployees() {
		List<Employee> list = new ArrayList<Employee>();
		Query q = entityManager.createQuery("from Employee");
		list = q.getResultList();
		return list;
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		
		Query q= entityManager.createQuery("from Employee where emp_id=:id");
		q.setParameter("id",id);
		Employee e1=(Employee) q.getSingleResult();
		entityManager.remove(e1);

	}

	@Override
	public Employee searchEmployee(int id) {
		// TODO Auto-generated method stub
		Employee empList = entityManager.find(Employee.class, id);
		return empList;
	}

	@Override
	public void updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		entityManager.merge(e);
	}
	
	

}
