/*package com.excelra.glpg.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelra.glpg.entity.EmployeeMaster;
import com.excelra.glpg.service.IEmployeeMasterService;

import io.swagger.annotations.Api;

*//**
 * @author harika.neelam
 *//*
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmployeeMasterController {

	private Logger log = LoggerFactory.getLogger(EmployeeMasterController.class);

	@Autowired
	private IEmployeeMasterService iEmployeeMasterService;
	
	@GetMapping("/getall/employees")
	public ResponseEntity<?> getAllProjectMasters() {
		log.info("REST request to get allemployees : {}");
		List<EmployeeMaster> employeeMastersList = iEmployeeMasterService.getAllEmployees();
		if (Objects.nonNull(employeeMastersList) && !employeeMastersList.isEmpty()) {
			return new ResponseEntity<>(employeeMastersList, HttpStatus.OK);
		}
		return new ResponseEntity<>(employeeMastersList, HttpStatus.NO_CONTENT);
	}

	
}
*/