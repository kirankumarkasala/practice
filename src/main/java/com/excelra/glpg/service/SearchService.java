package com.excelra.glpg.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelra.glpg.entity.AreaMaster;
import com.excelra.glpg.entity.Constants;
import com.excelra.glpg.entity.EmployeeMaster;
import com.excelra.glpg.entity.PersonLeadStakeholder;
import com.excelra.glpg.entity.PrioritizationMaster;
import com.excelra.glpg.entity.ProjectStatusDetail;
import com.excelra.glpg.entity.RiskMaster;
import com.excelra.glpg.model.AdvSearchDropDwnDto;
import com.excelra.glpg.model.AreaMasterDTO;
import com.excelra.glpg.model.EmployeeDTO;
import com.excelra.glpg.model.LeadDto;
import com.excelra.glpg.model.PersonIntakeDTO;
import com.excelra.glpg.model.PrioritizationMasterDTO;
import com.excelra.glpg.model.RiskMasterDTO;
import com.excelra.glpg.model.SearchDto;
import com.excelra.glpg.repository.AreaMasterRepository;
import com.excelra.glpg.repository.EmployeeMasterRepository;
import com.excelra.glpg.repository.PersonLeadStakeholderRepository;
import com.excelra.glpg.repository.PrioritizationMasterRepository;
import com.excelra.glpg.repository.ProjectDescriptionRepository;
import com.excelra.glpg.repository.ProjectStatusDetailRepository;
import com.excelra.glpg.repository.RiskMasterRepository;

@Service
public class SearchService implements ISearchService {

	private Logger log = LoggerFactory.getLogger(SearchService.class);

	@Autowired
	private EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	private ProjectStatusDetailRepository projectStatusDetailRepository;

	@Autowired
	private AreaMasterRepository areaMasterRepository;

	@Autowired
	private ProjectDescriptionRepository projectDescriptionRepository;
	
	@Autowired
	private PersonLeadStakeholderRepository personLeadStakeholderRepository;
	
	@Autowired
	private RiskMasterRepository riskMasterRepository;
	
	@Autowired
	private PrioritizationMasterRepository prioritizationMasterRepository;

	@Override
	public List<String> getAllSearchBy(SearchDto searchDto) {

		log.info("getAllSearchBy is called");

		if (Objects.nonNull(searchDto.getName()) && Objects.nonNull(searchDto.getValue()) ) {
			
			if (searchDto.getName().equalsIgnoreCase(Constants.GLPG_Project_Id)) {
				
				List<String> glpgProjIdsList = projectStatusDetailRepository.findAllByGlpgProjectIdContainingIgnoreCaseAndStatusNot(searchDto.getValue().toUpperCase());
				
				log.info("project-----{}", glpgProjIdsList.size());
				return glpgProjIdsList;

			} else if (searchDto.getName().equalsIgnoreCase(Constants.GLPG_Compound_Id)) {
				List<String> glpgCompoundIdsList = projectStatusDetailRepository.findAllByGlpgCompoundIdsContainingIgnoreCaseAndStatusNot(searchDto.getValue().toUpperCase());
			
				log.info("project-----{}", glpgCompoundIdsList.size());
				return glpgCompoundIdsList;
				
			} else if (searchDto.getName().equalsIgnoreCase(Constants.AREA)) {
				List<AreaMaster> areaMasterList = areaMasterRepository.findTop6ByAreaContainingIgnoreCaseOrderByAreaAsc(searchDto.getValue());

				log.info("project-----{}", areaMasterList.size());
				List<String> areaMasterList1 = new ArrayList<>();
				for (AreaMaster am : areaMasterList) {
					if (Objects.nonNull(am.getArea()))
						areaMasterList1.add(am.getArea());
				}
				return areaMasterList1;
			} else if (searchDto.getName().equalsIgnoreCase(Constants.TITLE)) {
				List<String> projectDescriptionList = projectDescriptionRepository
						.getAllByTitleIgnoreCaseByStatus(searchDto.getValue().toUpperCase());

				log.info("project-----{}", projectDescriptionList.size());
				return projectDescriptionList;
			} else if (searchDto.getName().equalsIgnoreCase(Constants.Project_Uid)) {
				List<String> projectUidsList = projectStatusDetailRepository.findDistinctTop6ByUIdContainingIgnoreCaseAndStatusNotOrderByUIdAsc(searchDto.getValue().toUpperCase());

				log.info("project-----{}", projectUidsList.size());
				return projectUidsList;
			}
		}

		return null;

	}

	@Override
	public List<EmployeeDTO> getAllSearchByEmployees(SearchDto searchDto) {
		
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();

		if (Objects.nonNull(searchDto.getName())) {
			List<EmployeeMaster> employeeMasters = employeeMasterRepository
					.findDistinctTop6ByEmployeeIdContainingIgnoreCase(searchDto.getValue());
			log.info("employee--------->{}", employeeMasters.size());

			for (EmployeeMaster emp : employeeMasters) {

				EmployeeDTO employeeDTO = new EmployeeDTO();
				employeeDTO.setEmployeeId(emp.getEmployeeId());
				employeeDTO.setEmployeeAccount(emp.getEmployeeAccount());
				employeeDTOs.add(employeeDTO);

			}
		}
		return employeeDTOs;
	}

	@Override
	public AdvSearchDropDwnDto getMetaData() {
		
		log.info("REST request to get metadata : {}");

		AdvSearchDropDwnDto advSearchDropDwnDto = new AdvSearchDropDwnDto();
		List<AreaMasterDTO> areaList = new ArrayList<>();
		Set<PersonIntakeDTO> personIntakeDTOSet = new HashSet<>();
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		Set<LeadDto> leadDTOs = new HashSet<>();
		Set<String> status = new HashSet<>();
		List<RiskMasterDTO> riskList = new ArrayList<>();
		List<PrioritizationMasterDTO> priorityList = new ArrayList<>();
		EmployeeMaster employeeMasters = null;
		List<PersonLeadStakeholder> personLeadStakeholder = personLeadStakeholderRepository.findAll();
		for (PersonLeadStakeholder personLeadStake : personLeadStakeholder) {
			if (Objects.nonNull(personLeadStake.getPersonIntakeId()) && !personLeadStake.getPersonIntakeId().contains("[NULL]")) {
				PersonIntakeDTO personIntakeDTO = new PersonIntakeDTO();

				employeeMasters = employeeMasterRepository.findByEmployeeId(personLeadStake.getPersonIntakeId());
				if (Objects.nonNull(employeeMasters)) {
					personIntakeDTO.setPersonIntakeId(personLeadStake.getPersonIntakeId());
					personIntakeDTO.setPersonName(employeeMasters.getEmployeeAccount());
				}
				personIntakeDTOSet.add(personIntakeDTO);
			}
			if (Objects.nonNull(personLeadStake.getLeadId()) &&  !personLeadStake.getLeadId().contains("[NULL]")) {
				employeeMasters = employeeMasterRepository.findByEmployeeId(personLeadStake.getLeadId());
				LeadDto leadDto = new LeadDto();
				if (Objects.nonNull(employeeMasters)) {
					leadDto.setLeadName(employeeMasters.getEmployeeAccount());
					leadDto.setLeadId(personLeadStake.getLeadId());
				}
				leadDTOs.add(leadDto);
			}
		}
		List<AreaMaster> areaMasterList = areaMasterRepository.findAll();
		for (AreaMaster areaMaster : areaMasterList) {
			AreaMasterDTO areaMasterDTO = new AreaMasterDTO();
			areaMasterDTO.setArea(areaMaster.getArea());
			areaMasterDTO.setAreaId(areaMaster.getAreaId());
			areaList.add(areaMasterDTO);
		}
		
		List<EmployeeMaster> em = employeeMasterRepository.findAll();
		for(EmployeeMaster employeeMaster : em)
		{
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeAccount(employeeMaster.getEmployeeAccount());
			employeeDTO.setEmployeeId(employeeMaster.getEmployeeId());
			employeeDTOs.add(employeeDTO);
		}
		List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository.findAll();
		for(ProjectStatusDetail psd : projectStatusDetails)
		{
			if (Objects.nonNull(psd.getStatus()) &&  !psd.getStatus().contains("[NULL]") &&  !psd.getStatus().contains("Deleted")) 
			status.add(psd.getStatus());
		}
		
		List<RiskMaster> riskMastersList = riskMasterRepository.findAll();
		for(RiskMaster riskMaster : riskMastersList) {
			RiskMasterDTO riskMasterDTO = new RiskMasterDTO();
			riskMasterDTO.setRiskCode(riskMaster.getRiskCode());
			riskMasterDTO.setRiskDetail(riskMaster.getRiskDetail());
			riskList.add(riskMasterDTO);	
			}
		List<PrioritizationMaster> prioritizationMastersList = prioritizationMasterRepository.findAll();
		for(PrioritizationMaster priorityMaster : prioritizationMastersList) {
			PrioritizationMasterDTO prioritizationMasterDTO = new PrioritizationMasterDTO();
			prioritizationMasterDTO.setTaPrioritizationCode(priorityMaster.getTaPrioritizationCode());
			prioritizationMasterDTO.setTaPrioritizationValue(priorityMaster.getTaPrioritizationValue());
			priorityList.add(prioritizationMasterDTO);
		}

		advSearchDropDwnDto.setPersonIntakeDTOList(personIntakeDTOSet);
		advSearchDropDwnDto.setLeadDtoList(leadDTOs);
		advSearchDropDwnDto.setAreaMasterDTOList(areaList);
		advSearchDropDwnDto.setEmployeeDTOList(employeeDTOs);
		advSearchDropDwnDto.setStatus(status);
		advSearchDropDwnDto.setRiskMasterDTOList(riskList);
		advSearchDropDwnDto.setPrioritizationMasterDTOList(priorityList);

		return advSearchDropDwnDto;
	}

}