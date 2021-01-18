package com.excelra.glpg.model;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {

	private String employeeAccount;
	private String employeeId;

	public String getEmployeeAccount() {
		return employeeAccount;
	}

	public void setEmployeeAccount(String employeeAccount) {
		this.employeeAccount = employeeAccount;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
