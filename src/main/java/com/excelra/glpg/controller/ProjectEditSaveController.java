package com.excelra.glpg.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelra.glpg.exception.ServiceException;
import com.excelra.glpg.model.BasicInfoDTO;
import com.excelra.glpg.model.ConsultingDTO;
import com.excelra.glpg.model.FteAllocationDTO;
import com.excelra.glpg.model.IntakePersonLeadDTO;
import com.excelra.glpg.model.MilestonesDelivDTO;
import com.excelra.glpg.model.ProjectDescriptionDTO;
import com.excelra.glpg.model.StakeholdersSponsorManageDTO;
import com.excelra.glpg.model.VendorDetailsDTO;
import com.excelra.glpg.service.IProjectEditSaveService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjectEditSaveController {

	private Logger log = LoggerFactory.getLogger(ProjectEditSaveController.class);

	@Autowired
	private IProjectEditSaveService iProjectEditSaveService;

	@PostMapping("/basicinfo/save")
	public ResponseEntity<?> saveBasicInfoData(@RequestBody BasicInfoDTO basicInfoDTO) {

		log.info("REST request to save basicinfo data : {}");
		String msg = null;
		if (Objects.isNull(basicInfoDTO.getuId()))
			return new ResponseEntity<>("projectUId is mandatory", HttpStatus.BAD_REQUEST);
		try {
			msg = iProjectEditSaveService.saveBasicInfoData(basicInfoDTO);
		} catch (Exception e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	
	@PostMapping("/projectdescription/save")
	public ResponseEntity<?> saveProjectDescription(@RequestBody ProjectDescriptionDTO projectDescriptionDTO) {

		log.info("REST request to save projectdescription data : {}");
		String msg = null;
		if (Objects.isNull(projectDescriptionDTO.getuId()))
			return new ResponseEntity<>("projectUId is mandatory", HttpStatus.BAD_REQUEST);
		try {
			msg = iProjectEditSaveService.saveProjectDescription(projectDescriptionDTO);
		} catch (Exception e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	
	@PostMapping("/milestonesdeliverables/save")
	public ResponseEntity<?> saveMilestonesDeliverablesData(@RequestBody MilestonesDelivDTO milestonesDelivDTO) {

		log.info("REST request to save MilestonesDeliverablesData data : {}");
		String msg = null;
		if (Objects.isNull(milestonesDelivDTO.getuId()))
			return new ResponseEntity<>("projectUId is mandatory", HttpStatus.BAD_REQUEST);
		try {
			msg = iProjectEditSaveService.saveMilestonesDeliverablesData(milestonesDelivDTO);
		} catch (Exception e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	
	@PostMapping("/intakepersonlead/save")
	public ResponseEntity<?> saveIntakePersonLeadData(@RequestBody IntakePersonLeadDTO intakePersonLeadDTO) {

		log.info("REST request to save intakePersonLead data : {}");
		if (Objects.isNull(intakePersonLeadDTO.getuId()))
			return new ResponseEntity<>("projectUId is mandatory", HttpStatus.BAD_REQUEST);
		try {
			 iProjectEditSaveService.saveIntakePersonLeadData(intakePersonLeadDTO);
		} catch (ServiceException e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("saved successfully", HttpStatus.OK);

	}
	
	@PostMapping("/stakeholdersponsrs/save")
	public ResponseEntity<?> saveStakeholderSponsrsData(@RequestBody StakeholdersSponsorManageDTO stakeholdersSponsorManageDTO) {

		log.info("REST request to save stakeholdersSponsorManage data : {}");
		if (Objects.isNull(stakeholdersSponsorManageDTO.getuId()))
			return new ResponseEntity<>("projectUId is mandatory", HttpStatus.BAD_REQUEST);
		try {
			 iProjectEditSaveService.saveStakeholderSponsrsData(stakeholdersSponsorManageDTO);
		} catch (ServiceException e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("saved successfully", HttpStatus.OK);

	}
	
	@PostMapping("/consulting/save")
	public ResponseEntity<?> saveConsultingData(@RequestBody ConsultingDTO consultingDTO) {

		log.info("REST request to save stakeholdersSponsorManage data : {}");
		if (Objects.isNull(consultingDTO.getuId()))
			return new ResponseEntity<>("projectUId is mandatory", HttpStatus.BAD_REQUEST);
		try {
			 iProjectEditSaveService.saveConsultingData(consultingDTO);
		} catch (ServiceException e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("saved successfully", HttpStatus.OK);
	}
	
	@PostMapping("/fteallocation/save")
	public ResponseEntity<?> saveFteAllocationData(@RequestBody FteAllocationDTO fteAllocationDTO) {

		log.info("REST request to save fteAllocation data : {}");
		if (Objects.isNull(fteAllocationDTO.getuId()))
			return new ResponseEntity<>("projectUId is mandatory", HttpStatus.BAD_REQUEST);
		try {
			 iProjectEditSaveService.saveFteAllocationData(fteAllocationDTO);
		} catch (ServiceException e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("saved successfully", HttpStatus.OK);
	}
	
	@PostMapping("/licensedetails/save")
	public ResponseEntity<?> saveLicenseDetailsData(@RequestBody VendorDetailsDTO vendorDetailsDTO) {

		log.info("REST request to save vendorDetails data : {}");
		String msg = null;
		if (Objects.isNull(vendorDetailsDTO.getuId()))
			return new ResponseEntity<>("projectUId is mandatory", HttpStatus.BAD_REQUEST);
		try {
			msg = iProjectEditSaveService.saveLicenseDetailsData(vendorDetailsDTO);
		} catch (ServiceException e) {
			log.error("Exception Caused..", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
