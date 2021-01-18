package com.excelra.glpg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sponsor_stakeholder_details")
public class SponsorStakeholderDetails {

	@Id
	@Column(name = "uid")
	private String uId;

	@Column(name = "sponsor_id")
	private String sponsorId;

	@Column(name = "stakeholders_id")
	private String stakeholdersId;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}

	public String getStakeholdersId() {
		return stakeholdersId;
	}

	public void setStakeholdersId(String stakeholdersId) {
		this.stakeholdersId = stakeholdersId;
	}

}
