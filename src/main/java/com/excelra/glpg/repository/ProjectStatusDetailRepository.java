package com.excelra.glpg.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.excelra.glpg.entity.ProjectStatusDetail;

@Repository
public interface ProjectStatusDetailRepository extends JpaRepository<ProjectStatusDetail, String> {

	@Query(value = "select distinct glpg_project_id from project_status_detail where upper(glpg_project_id) like %:glpgProjectId% and (status <> 'Deleted' or status is null) order by glpg_project_id asc limit 6", nativeQuery = true)
	List<String> findAllByGlpgProjectIdContainingIgnoreCaseAndStatusNot(@Param("glpgProjectId")  String glpgProjectId);

	@Query(value = "select distinct glpg_compound_ids from project_status_detail where upper(glpg_compound_ids) like %:glpgCompoundIds% and (status <> 'Deleted' or status is null) order by glpg_compound_ids asc limit 6", nativeQuery = true)
	List<String> findAllByGlpgCompoundIdsContainingIgnoreCaseAndStatusNot(@Param("glpgCompoundIds") String glpgCompoundIds);

	@Query(value = "select distinct uid from project_status_detail where upper(uid) like %:uId% and (status <> 'Deleted' or status is null) order by uid asc limit 6", nativeQuery = true)
	List<String> findDistinctTop6ByUIdContainingIgnoreCaseAndStatusNotOrderByUIdAsc(@Param("uId") String uId);

	ProjectStatusDetail findTopByUIdContainingOrderByUIdDesc(String uId);

	ProjectStatusDetail findAllByUId(String uId);

	List<ProjectStatusDetail> findByUIdIn(List<String> ids, Pageable pageable);

	@Query(value = "SELECT psd from ProjectStatusDetail psd  where (psd.uId in ?1 and (psd.status <> ?2 or psd.status is NULL))")
	List<ProjectStatusDetail> getAllByUIdInAndStatusNot(List<String> ids, String Status);

	@Query(value = "SELECT psd from ProjectStatusDetail psd  where (psd.status <> ?1 or psd.status is NULL)")
	List<ProjectStatusDetail> getAllByStatusNot(String Status);

	@Query(value = "select  psd from ProjectStatusDetail psd where (psd.glpgProjectId like %?1%)")
	List<ProjectStatusDetail> getByGlpgProjectUid(String glpgProjectUid);

	List<ProjectStatusDetail> findByGlpgCompoundIdsContainingIgnoreCase(String glpgCompoundIds);

	@Query(value = "select  psd from ProjectStatusDetail psd where (psd.uId like %?1%)")
	List<ProjectStatusDetail> getByProjectUid(String projectUid);

	@Query(value = "select  psd from ProjectStatusDetail psd where psd.areaId = ?1")
	List<ProjectStatusDetail> getAreaId(String areaId);

	@Query(value = "select  psd from ProjectStatusDetail psd where (psd.startDate >= ?1 and psd.startDate <= ?2 )")
	List<ProjectStatusDetail> getUidsByStartDate(Date fromDate, Date endDate);

	@Query(value = "select  psd from ProjectStatusDetail psd where psd.areaId in ?1")
	List<ProjectStatusDetail> getUidsByArea(List<String> areaIds);

	ProjectStatusDetail findByUId(String uId);

	List<ProjectStatusDetail> findByUIdIn(List<String> projectUids);

	// sorting based on column type UId
	List<ProjectStatusDetail> findByUIdInOrderByUIdAsc(List<String> projectUids);

	List<ProjectStatusDetail> findByUIdInOrderByUIdDesc(List<String> projectUids);

	// sorting based on column type status
	@Query(value = "select psd from ProjectStatusDetail psd where psd.uId in ?1 order BY case WHEN status = 'Proposed' THEN 1 WHEN status = 'Planned'"
			+ "THEN 2 WHEN status = 'Ongoing' THEN 3  WHEN status = 'Completed' THEN 4"
			+ "WHEN status = 'OnHold' THEN 5 WHEN status = 'Cancelled' THEN 6 WHEN status = 'Duplicate' THEN 7 ELSE 8 END,status")
	List<ProjectStatusDetail> getUIdInOrderByStatusAsc(List<String> projectUids);

	@Query(value = "select psd from ProjectStatusDetail psd where psd.uId in ?1 order BY case WHEN status = 'Proposed' THEN 7 WHEN status = 'Planned'"
			+ "THEN 6 WHEN status = 'Ongoing' THEN 5  WHEN status = 'Completed' THEN 4"
			+ "WHEN status = 'OnHold' THEN 3 WHEN status = 'Cancelled' THEN 2 WHEN status = 'Duplicate' THEN 1 ELSE 8 END,status")
	List<ProjectStatusDetail> getUIdInOrderByStatusDesc(List<String> projectUids);
	// sorting based on column type glpgProjectId
	List<ProjectStatusDetail> findByUIdInOrderByGlpgProjectIdAsc(List<String> projectUids);

	List<ProjectStatusDetail> findByUIdInOrderByGlpgProjectIdDesc(List<String> projectUids);

	// sorting based on column type startDate
	List<ProjectStatusDetail> findByUIdInOrderByStartDateAsc(List<String> projectUids);

	List<ProjectStatusDetail> findByUIdInOrderByStartDateDesc(List<String> projectUids);

	// sorting based on column type area
	@Query(value = "select psd from ProjectStatusDetail psd join AreaMaster am on psd.areaId = am.areaId where psd.uId in ?1 order by am.area asc")
	List<ProjectStatusDetail> getByUIdInOrderByAreaAsc(List<String> projectUids);

	@Query(value = "select psd from ProjectStatusDetail psd join AreaMaster am on psd.areaId = am.areaId where psd.uId in ?1 order by am.area desc")
	List<ProjectStatusDetail> getByUIdInOrderByAreaDesc(List<String> projectUids);

	// sorting on column type title
	@Query(value = "select psd from ProjectStatusDetail psd join ProjectDescription pd on psd.uId = pd.uId where psd.uId in ?1 order by pd.title asc")
	List<ProjectStatusDetail> getByUIdInOrderByTitleAsc(List<String> projectUids);

	@Query(value = "select psd from ProjectStatusDetail psd join ProjectDescription pd on psd.uId = pd.uId where psd.uId in ?1 order by pd.title desc")
	List<ProjectStatusDetail> getByUIdInOrderByTitleDesc(List<String> projectUids);

	// sorting based on column type lead

	@Query(value = "select psd from ProjectStatusDetail psd join PersonLeadStakeholder plsh on psd.uId = plsh.uId join EmployeeMaster em on plsh.leadId = em.employeeId where psd.uId in ?1 order by em.employeeAccount asc")
	List<ProjectStatusDetail> getByUIdInOrderByleadAsc(List<String> projectUids);

	@Query(value = "select psd from ProjectStatusDetail psd join PersonLeadStakeholder plsh on psd.uId = plsh.uId join EmployeeMaster em on plsh.leadId = em.employeeId where psd.uId in ?1 order by em.employeeAccount desc")
	List<ProjectStatusDetail> getByUIdInOrderByLeadDesc(List<String> projectUids);

	// sorting based on column type sponsors

	@Query(value = "select psd from ProjectStatusDetail psd join SponsorStakeholderDetails sshd on psd.uId = sshd.uId join EmployeeMaster em on substring(sshd.sponsorId,1,6) = em.employeeId where psd.uId in ?1 order by em.employeeAccount asc")
	List<ProjectStatusDetail> getByUIdInOrderBySponsorsAsc(List<String> projectUids);

	@Query(value = "select psd from ProjectStatusDetail psd join SponsorStakeholderDetails sshd on psd.uId = sshd.uId join EmployeeMaster em on substring(sshd.sponsorId,1,6) = em.employeeId where psd.uId in ?1 order by em.employeeAccount desc")
	List<ProjectStatusDetail> getByUIdInOrderBySponsorsDesc(List<String> projectUids);
	
	// sorting based on column type sponsors

	@Query(value = "select psd from ProjectStatusDetail psd join SponsorStakeholderDetails sshd on psd.uId = sshd.uId join EmployeeMaster em on substring(sshd.stakeholdersId,1,6) = em.employeeId where psd.uId in ?1 order by em.employeeAccount asc")
	List<ProjectStatusDetail> getByUIdInOrderByStakeHoldersAsc(List<String> projectUids);

	@Query(value = "select psd from ProjectStatusDetail psd join SponsorStakeholderDetails sshd on psd.uId = sshd.uId join EmployeeMaster em on substring(sshd.stakeholdersId,1,6) = em.employeeId where psd.uId in ?1 order by em.employeeAccount desc")
	List<ProjectStatusDetail> getByUIdInOrderByStakeHoldersDesc(List<String> projectUids);

}
