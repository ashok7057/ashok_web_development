package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;

	public Employee saveEmp(Employee emp) {
	   return empRepo.save(emp);

	}

	public Employee findById(int id) {
		
		  Optional<Employee> emp=empRepo.findById(id);
		  if(emp.isPresent()) { 
			  Employee emp1=emp.get();
			  return emp1; } 
		  else return null;
	    //return empRepo.findById(id).orElse(null)
	}
	
	public Employee findByName(String name) {
		
		  return empRepo.findByfirstName(name);
	}
	
	public List<Employee> findAll(){
		return empRepo.findAll();
	}
	
	public Employee updateEmp( Employee emp2,int id) {
		 Optional<Employee> emp=empRepo.findById(id);
		  Employee emp1=emp.get();
		  if(emp.isPresent()) {
		  emp1.setFirstName(emp2.getFirstName());
		  emp1.setLastName(emp2.getLastName());
		  emp1.setEmail(emp2.getEmail());
		  return empRepo.save(emp1);
		  }
		  else {
			  return null;
		  }
		
	}
	
	public String deleteEmp(int id) {
		Optional<Employee> emp=empRepo.findById(id);
		  Employee emp1=emp.get();
		  empRepo.delete(emp1);
		  return "Employee deleted successfully";
	}

}
