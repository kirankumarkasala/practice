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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excelra.glpg.exception.ServiceException;
import com.excelra.glpg.model.CreateProjectDTO;
import com.excelra.glpg.model.ProjectEditMainDTO;
import com.excelra.glpg.model.ProjectMasterDTO;
import com.excelra.glpg.model.SearchInputs;
import com.excelra.glpg.model.ProjectMastersData;
import com.excelra.glpg.model.SearchOrderInputs;
import com.excelra.glpg.service.IProjectService;
import com.excelra.glpg.utils.StringUtility;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectDataController {
	
	
	private Logger log = LoggerFactory.getLogger(ProjectDataController.class);

	@Autowired
	private IProjectService iProjectService;
	
	
	@Autowired
	private StringUtility stringUtility; 
	
	/*@PostMapping("/getall/projects")
	public ResponseEntity<?> getAllProjects(@RequestBody SearchInputs searchInputs) {
		
		log.info("REST request to get all projects : {}");
		
		if(Objects.isNull(searchInputs.getLimit()) || Objects.isNull(searchInputs.getPageNo()))
			return new ResponseEntity<>("limit and pageno are mandatory" , HttpStatus.BAD_REQUEST);
		
		List<ProjectMasterDTO> projectMasters = iProjectService.getAllProjects(searchInputs);
		if (Objects.nonNull(projectMasters) && !projectMasters.isEmpty()) {
			return new ResponseEntity<>(projectMasters, HttpStatus.OK);
		}
		return new ResponseEntity<>(projectMasters, HttpStatus.NO_CONTENT);
	}*/
	
	@PostMapping("/getall/projects")
	public ResponseEntity<?> getAllProjects(@RequestBody SearchOrderInputs searchOrderInputs) {
		
		log.info("REST request to get all projects : {}");
		
		if(Objects.isNull(searchOrderInputs.getLimit()) || Objects.isNull(searchOrderInputs.getPageNo())
				|| Objects.isNull(searchOrderInputs.getOrder()) || Objects.isNull(searchOrderInputs.getColumnName()))
			return new ResponseEntity<>("limit , pageno , order , columnname  are mandatory" , HttpStatus.BAD_REQUEST);
		
		if (Objects.nonNull(searchOrderInputs.getSearchBy())) {
			if (searchOrderInputs.getSearchBy().isEmpty())
			searchOrderInputs.setSearchBy(null);
		}
		if (Objects.nonNull(searchOrderInputs.getSearchName())) {
			if (searchOrderInputs.getSearchName().isEmpty())
				searchOrderInputs.setSearchName(null);
		}
		if (Objects.nonNull(searchOrderInputs.getAdvanceSearch())) {

			if (Objects.nonNull(searchOrderInputs.getAdvanceSearch().getAreaId())
					&& searchOrderInputs.getAdvanceSearch().getAreaId().isEmpty())
				searchOrderInputs.getAdvanceSearch().setAreaId(null);

			if (Objects.nonNull(searchOrderInputs.getAdvanceSearch().getGlpgProjectId())
					&& searchOrderInputs.getAdvanceSearch().getGlpgProjectId().isEmpty())
				searchOrderInputs.getAdvanceSearch().setGlpgProjectId(null);
			
			if (Objects.nonNull(searchOrderInputs.getAdvanceSearch().getIntakePersonId())
					&& searchOrderInputs.getAdvanceSearch().getIntakePersonId().isEmpty())
				searchOrderInputs.getAdvanceSearch().setIntakePersonId(null);
			
			if (Objects.nonNull(searchOrderInputs.getAdvanceSearch().getLeadId())
					&& searchOrderInputs.getAdvanceSearch().getLeadId().isEmpty())
				searchOrderInputs.getAdvanceSearch().setLeadId(null);
			if (Objects.isNull(searchOrderInputs.getAdvanceSearch().getLeadId())
					&& Objects.isNull(searchOrderInputs.getAdvanceSearch().getGlpgProjectId())
					&& Objects.isNull(searchOrderInputs.getAdvanceSearch().getIntakePersonId())
					&& Objects.isNull(searchOrderInputs.getAdvanceSearch().getAreaId())
					&& Objects.isNull(searchOrderInputs.getAdvanceSearch().getFromDate())
					&& Objects.isNull(searchOrderInputs.getAdvanceSearch().getToDate())) {
				searchOrderInputs.setAdvanceSearch(null);
			}		
		}
		ProjectMastersData projectMastersData = null;
		try {
			projectMastersData = iProjectService.getAllProjectsSortBy(searchOrderInputs);
		} catch (ServiceException e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
		if (Objects.nonNull(projectMastersData) && !projectMastersData.getProjectMasterDTOs().isEmpty()) {
			return new ResponseEntity<>(projectMastersData, HttpStatus.OK);
		}
		return new ResponseEntity<>(projectMastersData, HttpStatus.NO_CONTENT);
	}
	

	@PostMapping("/project-data/save")
	public ResponseEntity<?> createProjectData(@RequestBody CreateProjectDTO createProjectDTO) {
		
		log.info("REST request to save Project data : {}");
		String msg = null;
		if (Objects.isNull(createProjectDTO))
			return new ResponseEntity<>("project data is mandatory", HttpStatus.BAD_REQUEST);
		try {
			  msg = iProjectService.createProjectData(createProjectDTO);
		} catch (Exception e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	
	@GetMapping("/getall/areas")
	public ResponseEntity<?> getAllAreaData() {
		log.info("REST request to get all  area data : {}");
		List<String> areasList = iProjectService.getAllAreaData();
		if (Objects.nonNull(areasList) && !areasList.isEmpty()) {
			return new ResponseEntity<>(areasList, HttpStatus.OK);
		}
		return new ResponseEntity<>(areasList, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(value = "/deletebyuid")
	public ResponseEntity<?> deleteProjectData(@RequestParam String projectUId) {

		log.info("Deleting project data ");
		if (projectUId == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		try {
			iProjectService.deleteProjectData(projectUId);

		} catch (ServiceException e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
	}

	@PostMapping(value = "/editProjectData")
	public ResponseEntity<?> EditProjectData(@RequestBody ProjectEditMainDTO projectEditMainDTO) {

		log.info("Updating project data ");
		if (Objects.isNull(projectEditMainDTO.getuId()))
			return new ResponseEntity<>("UId is mandatory", HttpStatus.BAD_REQUEST);
		try {
			iProjectService.EditProjectData(projectEditMainDTO);
		} catch (ServiceException e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("updated successfully", HttpStatus.OK);
	}
}
