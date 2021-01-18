package com.excelra.glpg.model;

import java.util.List;
import java.util.Set;

public class AdvSearchDropDwnDto {

	private Set<PersonIntakeDTO> personIntakeDTOList;

	private Set<LeadDto> leadDtoList;

	private List<AreaMasterDTO> areaMasterDTOList;
	
	private List<EmployeeDTO> employeeDTOList;
	
	private Set<String> status;
	
	private List<RiskMasterDTO> riskMasterDTOList;

	private List<PrioritizationMasterDTO> prioritizationMasterDTOList;

	public Set<PersonIntakeDTO> getPersonIntakeDTOList() {
		return personIntakeDTOList;
	}

	public void setPersonIntakeDTOList(Set<PersonIntakeDTO> personIntakeDTOList) {
		this.personIntakeDTOList = personIntakeDTOList;
	}

	public Set<LeadDto> getLeadDtoList() {
		return leadDtoList;
	}

	public void setLeadDtoList(Set<LeadDto> leadDtoList) {
		this.leadDtoList = leadDtoList;
	}

	public List<AreaMasterDTO> getAreaMasterDTOList() {
		return areaMasterDTOList;
	}

	public void setAreaMasterDTOList(List<AreaMasterDTO> areaMasterDTOList) {
		this.areaMasterDTOList = areaMasterDTOList;
	}

	public List<EmployeeDTO> getEmployeeDTOList() {
		return employeeDTOList;
	}

	public void setEmployeeDTOList(List<EmployeeDTO> employeeDTOList) {
		this.employeeDTOList = employeeDTOList;
	}

	public Set<String> getStatus() {
		return status;
	}

	public void setStatus(Set<String> status) {
		this.status = status;
	}

	public List<RiskMasterDTO> getRiskMasterDTOList() {
		return riskMasterDTOList;
	}

	public void setRiskMasterDTOList(List<RiskMasterDTO> riskMasterDTOList) {
		this.riskMasterDTOList = riskMasterDTOList;
	}

	public List<PrioritizationMasterDTO> getPrioritizationMasterDTOList() {
		return prioritizationMasterDTOList;
	}

	public void setPrioritizationMasterDTOList(List<PrioritizationMasterDTO> prioritizationMasterDTOList) {
		this.prioritizationMasterDTOList = prioritizationMasterDTOList;
	}

		
	

}