package com.excelra.glpg.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelra.glpg.entity.Constants;
import com.excelra.glpg.model.AdvSearchDropDwnDto;
import com.excelra.glpg.model.EmployeeDTO;
import com.excelra.glpg.model.SearchDto;
import com.excelra.glpg.service.ISearchService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SearchController {

	private Logger log = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private ISearchService iSearchService;

	@PostMapping("/searchby/names")
	public ResponseEntity<?> getAllSearchBy(@RequestBody SearchDto searchDto) {

		log.info("REST request to get all projects : {}");

		if (Objects.isNull(searchDto.getName()) || Objects.isNull(searchDto.getValue()))
			return new ResponseEntity<>("name and values are mandatory", HttpStatus.BAD_REQUEST);

		if (Objects.nonNull(searchDto.getName())) {

			if (searchDto.getName().equalsIgnoreCase(Constants.GLPG_Project_Id)) {
				List<String> glpgProjectIdList = iSearchService.getAllSearchBy(searchDto);
				if (Objects.isNull(glpgProjectIdList) || glpgProjectIdList.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(glpgProjectIdList, HttpStatus.OK);
			}

			else if (searchDto.getName().equalsIgnoreCase(Constants.GLPG_Compound_Id)) {
				List<String> glpgCompoundIdList = iSearchService.getAllSearchBy(searchDto);
				if (Objects.isNull(glpgCompoundIdList) || glpgCompoundIdList.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(glpgCompoundIdList, HttpStatus.OK);
			}

			else if (searchDto.getName().equalsIgnoreCase(Constants.AREA)) {
				List<String> areaList = iSearchService.getAllSearchBy(searchDto);
				if (Objects.isNull(areaList) || areaList.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(areaList, HttpStatus.OK);
			} else if (searchDto.getName().equalsIgnoreCase(Constants.TITLE)) {
				List<String> titleList = iSearchService.getAllSearchBy(searchDto);
				if (Objects.isNull(titleList) || titleList.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(titleList, HttpStatus.OK);
			} else if (searchDto.getName().equalsIgnoreCase(Constants.Project_Uid)) {
				List<String> projectUidList = iSearchService.getAllSearchBy(searchDto);
				if (Objects.isNull(projectUidList) || projectUidList.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(projectUidList, HttpStatus.OK);
			} else if (searchDto.getName().equalsIgnoreCase(Constants.EMPLOYEE_ID)) {

				List<EmployeeDTO> employeeDTOs = iSearchService.getAllSearchByEmployees(searchDto);
				if (Objects.isNull(employeeDTOs) || employeeDTOs.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
			} else

				return new ResponseEntity<>("name is not matched with any search", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/get/metadata")
	public ResponseEntity<?> getMetaData() {
		
		log.info("REST request to get metadata : {}");
		
		AdvSearchDropDwnDto advSearchDropDwnDto = iSearchService.getMetaData();
		if (Objects.nonNull(advSearchDropDwnDto)) {
			return new ResponseEntity<>(advSearchDropDwnDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(advSearchDropDwnDto, HttpStatus.NO_CONTENT);
	}
	

}
