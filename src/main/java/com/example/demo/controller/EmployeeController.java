package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) 
	{
	     Employee saveEmp=empService.saveEmp(emp);
	     
		return new ResponseEntity(saveEmp,HttpStatus.OK);
		
	}
	@GetMapping("/getemp/{id}")
	public ResponseEntity<Employee> findById(@PathVariable int id){
		
		return new ResponseEntity(empService.findById(id),HttpStatus.OK);
	}
	@GetMapping("/getemp/{firstName}")
	public ResponseEntity<Employee> findByfirstName(@PathVariable String firstName){
		
		return new ResponseEntity(empService.findByName(firstName),HttpStatus.OK);
	}
	@GetMapping("/getAllEmp")
	public ResponseEntity<List<Employee>> findAllEmployees(){
		return new ResponseEntity<List<Employee>>(empService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/getAllEmpFirstNames")
	public ResponseEntity<List<String>> EmployeeFirstNames(){
		
		List<String> empList=empService.findAll().stream().map(emp->emp.getFirstName()).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(empList, HttpStatus.OK);
	}
	
	@PutMapping("/updateEmp/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp,@PathVariable int id){
		
		return new ResponseEntity(empService.updateEmp(emp, id),HttpStatus.OK);
		
	}
	@DeleteMapping("/deleteEmp/{id}")
	public String deleteEmployee(@PathVariable int id){
		empService.deleteEmp(id);
		return "Employee deleted successfully";
		
	}

}
