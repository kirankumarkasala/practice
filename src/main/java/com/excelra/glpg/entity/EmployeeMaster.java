package com.excelra.glpg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_master")
public class EmployeeMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_department")
	private String employeeDepartment;

	@Column(name = "employee_account")
	private String employeeAccount;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public String getEmployeeAccount() {
		return employeeAccount;
	}

	public void setEmployeeAccount(String employeeAccount) {
		this.employeeAccount = employeeAccount;
	}

}
