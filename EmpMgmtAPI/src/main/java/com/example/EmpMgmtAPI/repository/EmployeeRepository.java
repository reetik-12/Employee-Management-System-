package com.example.EmpMgmtAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmpMgmtAPI.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
