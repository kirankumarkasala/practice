package com.excelra.glpg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.excelra.glpg.entity.AreaMaster;
import com.excelra.glpg.entity.ProjectDescription;

public interface AreaMasterRepository extends JpaRepository<AreaMaster, String>{

	List<AreaMaster> findTop6ByAreaContainingIgnoreCaseOrderByAreaAsc(String area);
	
	AreaMaster findByArea(String area);
	
	List<AreaMaster> findByAreaContainingIgnoreCase(String area);
	

}
