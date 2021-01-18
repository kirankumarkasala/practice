package com.excelra.glpg.model;

import java.util.List;

public class ProjectMastersData {

	private List<ProjectMasterDTO> projectMasterDTOs;
	private Integer recordsCount;

	public List<ProjectMasterDTO> getProjectMasterDTOs() {
		return projectMasterDTOs;
	}

	public void setProjectMasterDTOs(List<ProjectMasterDTO> projectMasterDTOs) {
		this.projectMasterDTOs = projectMasterDTOs;
	}

	public Integer getRecordsCount() {
		return recordsCount;
	}

	public void setRecordsCount(Integer recordsCount) {
		this.recordsCount = recordsCount;
	}

}
