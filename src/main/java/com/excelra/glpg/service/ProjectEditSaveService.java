package com.excelra.glpg.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelra.glpg.entity.AreaMaster;
import com.excelra.glpg.entity.Constants;
import com.excelra.glpg.entity.EmployeeMaster;
import com.excelra.glpg.entity.PersonLeadStakeholder;
import com.excelra.glpg.entity.PrioritizationMaster;
import com.excelra.glpg.entity.ProjectDescription;
import com.excelra.glpg.entity.ProjectStatusDetail;
import com.excelra.glpg.entity.ResourceAllocation;
import com.excelra.glpg.entity.ResourceDetails;
import com.excelra.glpg.entity.SponsorStakeholderDetails;
import com.excelra.glpg.entity.VendorDetails;
import com.excelra.glpg.exception.ServiceException;
import com.excelra.glpg.model.BasicInfoDTO;
import com.excelra.glpg.model.ConsultingDTO;
import com.excelra.glpg.model.FteAllocationDTO;
import com.excelra.glpg.model.IntakePersonLeadDTO;
import com.excelra.glpg.model.MilestonesDelivDTO;
import com.excelra.glpg.model.ProjectDescriptionDTO;
import com.excelra.glpg.model.StakeholdersSponsorManageDTO;
import com.excelra.glpg.model.VendorDetailsDTO;
import com.excelra.glpg.repository.AreaMasterRepository;
import com.excelra.glpg.repository.EmployeeMasterRepository;
import com.excelra.glpg.repository.PersonLeadStakeholderRepository;
import com.excelra.glpg.repository.PrioritizationMasterRepository;
import com.excelra.glpg.repository.ProjectDescriptionRepository;
import com.excelra.glpg.repository.ProjectStatusDetailRepository;
import com.excelra.glpg.repository.ResourceAllocationRepository;
import com.excelra.glpg.repository.ResourceDetailsRepository;
import com.excelra.glpg.repository.RiskMasterRepository;
import com.excelra.glpg.repository.SponsorStakeholderDetailsRepository;
import com.excelra.glpg.repository.VendorDetailsRepository;

@Service
public class ProjectEditSaveService implements IProjectEditSaveService {

	private Logger log = LoggerFactory.getLogger(ProjectService.class);

	@Autowired
	ProjectStatusDetailRepository projectStatusDetailRepository;

	@Autowired
	ProjectDescriptionRepository projectDescriptionRepository;

	@Autowired
	AreaMasterRepository areaMasterRepository;

	@Autowired
	PersonLeadStakeholderRepository personLeadStakeholderRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	SponsorStakeholderDetailsRepository sponsorStakeholderDetailsRepository;

	@Autowired
	ResourceAllocationRepository resourceAllocationRepository;

	@Autowired
	ResourceDetailsRepository resourceDetailsRepository;

	@Autowired
	VendorDetailsRepository vendorDetailsRepository;

	@Autowired
	RiskMasterRepository riskMasterRepository;

	@Autowired
	PrioritizationMasterRepository prioritizationMasterRepository;

	@Override
	public String saveBasicInfoData(BasicInfoDTO basicInfoDTO) {
		log.info("updating project data ");
		String msg = null;
		String areaId = null;
		String taPrioritizationCode = null;
		ProjectStatusDetail projectStatusDetail = projectStatusDetailRepository.findByUId(basicInfoDTO.getuId());
		if (Objects.isNull(projectStatusDetail)) {
			throw new ServiceException("UId is not found");
		}
		if (Objects.nonNull(basicInfoDTO.getArea())) {
			AreaMaster areaMaster = areaMasterRepository.findByArea(basicInfoDTO.getArea());
			if (Objects.nonNull(areaMaster)) {
				areaId = areaMaster.getAreaId();
			}
		}
		if (Objects.nonNull(basicInfoDTO.getTaPrioritizationValue())) {
			PrioritizationMaster prioritizationMaster = prioritizationMasterRepository
					.findByTaPrioritizationValue(basicInfoDTO.getTaPrioritizationValue());
			if (Objects.nonNull(prioritizationMaster)) {
				taPrioritizationCode = prioritizationMaster.getTaPrioritizationCode();
			}
		}

		if (Objects.nonNull(projectStatusDetail)) {
			projectStatusDetail.setGlpgProjectId(basicInfoDTO.getGlpgProjectId().toUpperCase());
			if (basicInfoDTO.getStartDate() != null) {
				projectStatusDetail.setStartDate(new Timestamp(basicInfoDTO.getStartDate().getTime()));
			}
			if (basicInfoDTO.getEstimatedDateEnd() != null) {
				projectStatusDetail.setEstimatedDateEnd(new Timestamp(basicInfoDTO.getEstimatedDateEnd().getTime()));
			}
			if (basicInfoDTO.getActualDateEnd() != null) {
				projectStatusDetail.setActualDateEnd(new Timestamp(basicInfoDTO.getActualDateEnd().getTime()));
			}
			if (basicInfoDTO.getArea() != null) {
				projectStatusDetail.setAreaId(areaId);
			}
			projectStatusDetail.setIsDevelopment(basicInfoDTO.getIsDevelopment());
			projectStatusDetail.setStatus(basicInfoDTO.getStatus());
			projectStatusDetail.setTaPrioritizationCode(taPrioritizationCode);
			projectStatusDetail.setRisksCode(basicInfoDTO.getRisksCode());
			projectStatusDetailRepository.save(projectStatusDetail);
			ProjectDescription projectDescription = projectDescriptionRepository.findByUId(basicInfoDTO.getuId());
			if (Objects.nonNull(projectDescription)) {

				projectDescription.setTitle(basicInfoDTO.getTitle());
				projectDescriptionRepository.save(projectDescription);
			}
			msg = "saved successfully";
		} else {
			msg = "not saved";
		}
		return msg;
	}

	@Override
	public String saveProjectDescription(ProjectDescriptionDTO projectDescriptionDTO) {

		log.info("updating projectdescription data ");
		String msg = null;
		ProjectDescription projectDescription = projectDescriptionRepository.findByUId(projectDescriptionDTO.getuId());
		if (Objects.nonNull(projectDescription)) {

			projectDescription.setGoals(projectDescriptionDTO.getGoals());
			projectDescription.setDescription(projectDescriptionDTO.getDescription());
			projectDescription.setBusinessValue(projectDescriptionDTO.getBusinessValue());
			projectDescription.setInScope(projectDescriptionDTO.getInScope());
			projectDescription.setOutScope(projectDescriptionDTO.getOutScope());
			projectDescriptionRepository.save(projectDescription);
			msg = "saved successfully";
		} else {
			msg = "not saved";
		}

		return msg;
	}

	@Override
	public String saveMilestonesDeliverablesData(MilestonesDelivDTO milestonesDelivDTO) {
		log.info("updating projectdescription data ");
		String msg = null;
		ProjectDescription projectDescription = projectDescriptionRepository.findByUId(milestonesDelivDTO.getuId());
		if (Objects.nonNull(projectDescription)) {

			projectDescription.setMilestones(milestonesDelivDTO.getMilestones());
			projectDescription.setDeliverables(milestonesDelivDTO.getDeliverables());
			projectDescription.setComments(milestonesDelivDTO.getComments());
			projectDescriptionRepository.save(projectDescription);
			msg = "saved successfully";
		} else {
			msg = "not saved";
		}

		return msg;
	}

	@Override
	public void saveIntakePersonLeadData(IntakePersonLeadDTO intakePersonLeadDTO) {
		log.info("updating IntakePersonLead data ");
		String msg = null;
		String personIntakeId = null;
		String leadId = null;
		if (Objects.nonNull(intakePersonLeadDTO.getIntakePerson())) {
			EmployeeMaster employeeMaster = employeeMasterRepository.findByEmployeeAccount(intakePersonLeadDTO.getIntakePerson());
			if (Objects.nonNull(employeeMaster)) {
				personIntakeId = employeeMaster.getEmployeeId();
			}
		}
		if (Objects.nonNull(intakePersonLeadDTO.getLead())) {
			EmployeeMaster employeeMasters = employeeMasterRepository.findByEmployeeAccount(intakePersonLeadDTO.getLead());
			if (Objects.nonNull(employeeMasters)) {
				leadId = employeeMasters.getEmployeeId();
			}
		}
		PersonLeadStakeholder personLeadStakeholder = new PersonLeadStakeholder();
		personLeadStakeholder = personLeadStakeholderRepository.findByUId(intakePersonLeadDTO.getuId());
		if (Objects.isNull(personLeadStakeholder)) {
			personLeadStakeholder = new PersonLeadStakeholder();
			personLeadStakeholder.setuId(intakePersonLeadDTO.getuId());
		}
		personLeadStakeholder.setPersonIntakeId(personIntakeId);
		personLeadStakeholder.setLeadId(leadId);
		if (intakePersonLeadDTO.getLeadRoleStartDate() != null) {
			personLeadStakeholder.setHeirarchyStartDate(new Timestamp(intakePersonLeadDTO.getLeadRoleStartDate().getTime()));
		}
		if (intakePersonLeadDTO.getLeadRoleEndDate() != null) {
			personLeadStakeholder.setHeirarchyEndDate(new Timestamp(intakePersonLeadDTO.getLeadRoleEndDate().getTime()));
		}
		personLeadStakeholderRepository.save(personLeadStakeholder);
	
		ResourceDetails resourceDetails = resourceDetailsRepository.findByUId(intakePersonLeadDTO.getuId());
		if (Objects.isNull(resourceDetails)) {
			resourceDetails = new ResourceDetails();
			resourceDetails.setuId(intakePersonLeadDTO.getuId());
		}
		if (intakePersonLeadDTO.getPersonRoleStartDate() != null) {
			resourceDetails.setPersonIntakeStartDate(new Timestamp(intakePersonLeadDTO.getPersonRoleStartDate().getTime()));
		}
		if (intakePersonLeadDTO.getPersonRoleEndDate() != null) {
			resourceDetails.setPersonIntakeEndDate(new Timestamp(intakePersonLeadDTO.getPersonRoleEndDate().getTime()));
		}
		resourceDetailsRepository.save(resourceDetails);
		
	}

	@Override
	public void saveStakeholderSponsrsData(StakeholdersSponsorManageDTO stakeholdersSponsorManageDTO) {
		log.info("updating StakeholdersSponsorManage data ");
		
		ResourceDetails resourceDetails = resourceDetailsRepository.findByUId(stakeholdersSponsorManageDTO.getuId());
		if (Objects.isNull(resourceDetails)) {
			resourceDetails = new ResourceDetails();
			resourceDetails.setuId(stakeholdersSponsorManageDTO.getuId());
		}
		resourceDetails.setManagementId(stakeholdersSponsorManageDTO.getManagementName());
		resourceDetailsRepository.save(resourceDetails);
		String sponsorIds = null;
		List<String> sponsorIdsList = new ArrayList<>();
		if (Objects.nonNull(stakeholdersSponsorManageDTO.getSponsors())) {
			String[] sponsorsNames = stakeholdersSponsorManageDTO.getSponsors().split(",");
			List<String> sponsorsList = Arrays.asList(sponsorsNames);
			sponsorIds = String.join(",", sponsorsList);
			log.info("sponsorids---------->{}", sponsorIds);
		}

		String stakeholdersIds = null;
		List<String> stakeholdersIdList = new ArrayList<>();
		if (Objects.nonNull(stakeholdersSponsorManageDTO.getStakeholders())) {
			String[] stakeholdersNames = stakeholdersSponsorManageDTO.getStakeholders().split(",");
			List<String> stakeholdersList = Arrays.asList(stakeholdersNames);
			stakeholdersIds = String.join(",", stakeholdersList);
			log.info("stakeholdersIds---------->{}", stakeholdersList);
		}
		SponsorStakeholderDetails sponsorStakeholderDetails = sponsorStakeholderDetailsRepository.findByUId(stakeholdersSponsorManageDTO.getuId());
		if (Objects.isNull(sponsorStakeholderDetails)) {
			sponsorStakeholderDetails = new SponsorStakeholderDetails();
			sponsorStakeholderDetails.setuId(stakeholdersSponsorManageDTO.getuId());
		}
		sponsorStakeholderDetails.setSponsorId(sponsorIds);
		sponsorStakeholderDetails.setStakeholdersId(stakeholdersIds);
		sponsorStakeholderDetailsRepository.save(sponsorStakeholderDetails);	
	}

	@Override
	public void saveConsultingData(ConsultingDTO consultingDTO) {
		log.info("updating consulting data ");
		
		ResourceDetails resourceDetails = resourceDetailsRepository.findByUId(consultingDTO.getuId());
		if (Objects.isNull(resourceDetails)) {
			resourceDetails = new ResourceDetails();
			resourceDetails.setuId(consultingDTO.getuId());
		}
		resourceDetails.setConsultingId(consultingDTO.getConsulting());
		resourceDetailsRepository.save(resourceDetails);
	}

	@Override
	public void saveFteAllocationData(FteAllocationDTO fteAllocationDTO) {
		log.info("updated fteAllocation data");
		
		ResourceAllocation resourceAllocation = resourceAllocationRepository.findByUId(fteAllocationDTO.getuId());
		if (Objects.isNull(resourceAllocation)) {
			resourceAllocation = new ResourceAllocation();
			resourceAllocation.setuId(fteAllocationDTO.getuId());
		}
		resourceAllocation.setResourcesConsulting(fteAllocationDTO.getConsulting());
		if (Objects.nonNull(fteAllocationDTO.getPersonIntakeId())) {
			resourceAllocation.setResourcesLead(fteAllocationDTO.getPersonIntakeId());
		}
		if (Objects.nonNull(fteAllocationDTO.getManagement())) {
			resourceAllocation.setResourcesManagement(fteAllocationDTO.getManagement());
		}
		if (fteAllocationDTO.getResourcesDateFrom() != null) {
			resourceAllocation.setResourcesDateFrom(new Timestamp(fteAllocationDTO.getResourcesDateFrom().getTime()));
		}
		if (fteAllocationDTO.getResourcesDateTo() != null) {
			resourceAllocation.setResourcesDateTo(new Timestamp(fteAllocationDTO.getResourcesDateTo().getTime()));
		}
		resourceAllocationRepository.save(resourceAllocation);
	}

	@Override
	public String saveLicenseDetailsData(VendorDetailsDTO vendorDetailsDTO) {
		log.info("updated vendordetails data");
		String msg = null;
		ResourceAllocation resourceAllocation = resourceAllocationRepository.findByUId(vendorDetailsDTO.getuId());
		if(Objects.nonNull(resourceAllocation)) {
			resourceAllocation.setResourceLicenses(vendorDetailsDTO.getLicenseUsed());
			resourceAllocationRepository.save(resourceAllocation);
		if (Objects.nonNull(vendorDetailsDTO.getLicenseUsed()) &&  Objects.nonNull(resourceAllocation.getResourceLicenses())) {
			if (vendorDetailsDTO.getLicenseUsed().equalsIgnoreCase(Constants.LICENSE_USED) && resourceAllocation.getResourceLicenses().equalsIgnoreCase(Constants.LICENSE_USED)) {
				VendorDetails vendorDetails = vendorDetailsRepository.findByUId(vendorDetailsDTO.getuId());
				if (Objects.isNull(vendorDetails)) {
					vendorDetails = new VendorDetails();
					vendorDetails.setuId(vendorDetailsDTO.getuId());
				}
				vendorDetails.setVendorName(vendorDetailsDTO.getVendorName());
				vendorDetails.setVendorDetails(vendorDetailsDTO.getVendorDetails());
				vendorDetails.setVendorType(vendorDetailsDTO.getVendorType());
				vendorDetails.setComments(vendorDetailsDTO.getVendorComments());
				vendorDetailsRepository.save(vendorDetails);
				msg = "saved successfully";
			}
			else {
				msg = "could not be saved, project has no license details";
			}
			
		}
		}
		return msg;
	}
	
	
}
