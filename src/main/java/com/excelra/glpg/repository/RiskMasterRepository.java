package com.excelra.glpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelra.glpg.entity.RiskMaster;

@Repository
public interface RiskMasterRepository extends JpaRepository<RiskMaster, String>{
	
	

}
