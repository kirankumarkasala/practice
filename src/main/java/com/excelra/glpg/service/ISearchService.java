package com.excelra.glpg.service;

import java.util.List;

import com.excelra.glpg.model.AdvSearchDropDwnDto;
import com.excelra.glpg.model.EmployeeDTO;
import com.excelra.glpg.model.SearchDto;

public interface ISearchService {


	List<String> getAllSearchBy(SearchDto searchDto);
	
	List<EmployeeDTO> getAllSearchByEmployees(SearchDto searchDto);

	AdvSearchDropDwnDto getMetaData();



}
