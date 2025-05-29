package com.example.EmpMgmtAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmpMgmtAPI.entity.Employee;
import com.example.EmpMgmtAPI.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository repo;
	//get all the Employee
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		
		List<Employee> employees=repo.findAll();
		return employees;
		
	}
	
	//localhost:8080/employees/1
	@GetMapping("/employees/{id}")
	public Employee getStudent(@PathVariable int id) {
		Employee emp = repo.findById(id).get();
		return emp;
		
	}
	

	
	@PostMapping("/employee/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createEmployee(@RequestBody Employee emp) {
		repo.save(emp);
		
	}
	
	@PutMapping("/employee/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmp) {
	    Optional<Employee> optionalEmp = repo.findById(id);

	    if (optionalEmp.isEmpty()) {
	        return ResponseEntity.notFound().build(); // 404 if not found
	    }

	    Employee existingEmp = optionalEmp.get();

	    existingEmp.setName(updatedEmp.getName());
	    existingEmp.setEmail(updatedEmp.getEmail());
	    existingEmp.setSalary(updatedEmp.getSalary());
	    existingEmp.setMobno(updatedEmp.getMobno());

	    Employee saved = repo.save(existingEmp);
	    return ResponseEntity.ok(saved);
	}
	
	@DeleteMapping("/employee/delete/{id}")
	public void removeStudent(@PathVariable int id)
	{
		Employee emp=repo.findById(id).get();
		repo.delete(emp);
		
	}


	
	

}
