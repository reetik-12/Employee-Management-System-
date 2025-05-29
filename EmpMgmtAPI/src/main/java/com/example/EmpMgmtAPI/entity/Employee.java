package com.example.EmpMgmtAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Unique ID
	private int id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String salary;
	@Column
	private String mobno;
	
	public Employee()
	{
		
	}
	
	
	public Employee(String name, String email, String salary, String mobno) {
		super();
		this.name = name;
		this.email = email;
		this.salary = salary;
		this.mobno = mobno;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getMobno() {
		return mobno;
	}
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", salary=" + salary + ", mobno=" + mobno
				+ "]";
	}
	
	

}
