package com.excelra.glpg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelra.glpg.entity.EmployeeMaster;



@Repository
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, String>{

	List<EmployeeMaster> findDistinctTop6ByEmployeeIdContainingIgnoreCase(String employeeId);

	EmployeeMaster findByEmployeeId(String employeeId);

	EmployeeMaster findByEmployeeAccount(String employeeAccount);

	//List<EmployeeMaster> findAllByEmployeeAccount(List<String> employeeAccount);

}
