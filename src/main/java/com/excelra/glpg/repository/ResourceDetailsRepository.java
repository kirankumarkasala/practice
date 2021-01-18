package com.excelra.glpg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.excelra.glpg.entity.ResourceDetails;

@Repository
public interface ResourceDetailsRepository extends JpaRepository<ResourceDetails, String>{

	ResourceDetails findByUId(String uId);
	
	@Query(value = "select  rd from ResourceDetails rd where (personIntakeId like %?1%)")
	List<ResourceDetails> getAllResources(String empId);

}
