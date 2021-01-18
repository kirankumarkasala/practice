package com.excelra.glpg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelra.glpg.entity.EmployeeMaster;
import com.excelra.glpg.repository.EmployeeMasterRepository;

@Service
public class EmployeeMasterService implements IEmployeeMasterService {

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	private Logger log = LoggerFactory.getLogger(EmployeeMasterService.class);

	@Override
	public List<EmployeeMaster> getAllEmployees() {
		
		log.info("list of employeess: {}", employeeMasterRepository.findAll() );
		
		return employeeMasterRepository.findAll();
	}
}
