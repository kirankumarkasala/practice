package com.excelra.glpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelra.glpg.entity.PrioritizationMaster;

@Repository
public interface PrioritizationMasterRepository extends JpaRepository<PrioritizationMaster, String>{

	PrioritizationMaster findByTaPrioritizationValue(String taPrioritizationValue);

}
