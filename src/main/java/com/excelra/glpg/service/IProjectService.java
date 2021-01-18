package com.excelra.glpg.service;

import java.util.List;

import com.excelra.glpg.entity.ProjectStatusDetail;
import com.excelra.glpg.model.CreateProjectDTO;
import com.excelra.glpg.model.ProjectEditMainDTO;
import com.excelra.glpg.model.ProjectMasterDTO;
import com.excelra.glpg.model.ProjectMastersData;
import com.excelra.glpg.model.SearchInputs;
import com.excelra.glpg.model.SearchOrderInputs;

public interface IProjectService {
	
	
	ProjectMastersData getAllProjectsSortBy(SearchOrderInputs searchOrderInputs);

	String createProjectData(CreateProjectDTO createProjectDTO);

	List<String> getAllAreaData();
	
	List<ProjectMasterDTO> getAllProjectsData(List<ProjectStatusDetail> projectStatusDetail);

	void deleteProjectData(String projectUId);

	void EditProjectData(ProjectEditMainDTO projectEditMainDTO);


}
