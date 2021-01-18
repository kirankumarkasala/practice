package com.excelra.glpg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.excelra.glpg.entity.PersonLeadStakeholder;

@Repository
public interface PersonLeadStakeholderRepository extends JpaRepository<PersonLeadStakeholder, String>{

	
	@Query(value = "select  plsh from PersonLeadStakeholder plsh where (leadId like %?1%) or (personIntakeId like %?1%)")
	List<PersonLeadStakeholder> getAllLeads(String empId);
	
	@Query(value = "select  plsh from PersonLeadStakeholder plsh where personIntakeId = ?1")
	List<PersonLeadStakeholder> getPersonIntakeId(String personIntakeId);
	
	@Query(value = "select  plsh from PersonLeadStakeholder plsh where leadId = ?1")
	List<PersonLeadStakeholder> getLeadId(String leadId);

	PersonLeadStakeholder findByUId(String uId);

}
