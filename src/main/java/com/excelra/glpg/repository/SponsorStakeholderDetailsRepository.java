package com.excelra.glpg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.excelra.glpg.entity.SponsorStakeholderDetails;

@Repository
public interface SponsorStakeholderDetailsRepository extends JpaRepository<SponsorStakeholderDetails, String>{
	
	
	@Query(value = "select  sshd from SponsorStakeholderDetails sshd where (sponsorId like %?1% or stakeholdersId like  %?1%)")
	List<SponsorStakeholderDetails> getAllBySponsorsOrStakeHolders(String empId);

	SponsorStakeholderDetails findByUId(String uId);

}
