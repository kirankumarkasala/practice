package com.excelra.glpg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.excelra.glpg.entity.ProjectDescription;
import com.excelra.glpg.entity.ProjectStatusDetail;

@Repository
public interface ProjectDescriptionRepository extends JpaRepository<ProjectDescription, String>{

	@Query(value = "select distinct title from project_description where uid in(select uid  from project_status_detail where  uid in(select distinct uid from project_description where upper(title) like %:title%) and (status <> 'Deleted' or status is null))order by title asc limit 6 ", nativeQuery = true)
	List<String> getAllByTitleIgnoreCaseByStatus(@Param("title") String title);
	
	@Query(value = "select  pd from ProjectDescription pd where areaId = ?1")
	List<ProjectDescription> getAreaId(String areaId);
	
	List<ProjectDescription> findByTitleContainingIgnoreCase(String title);

    ProjectDescription findByUId(String uId);
    
}
