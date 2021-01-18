package com.excelra.glpg.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person_lead_stakeholder")
public class PersonLeadStakeholder {

	@Id
	@Column(name = "uid")
	private String uId;

	@Column(name = "person_intake_id")
	private String personIntakeId;

	@Column(name = "lead_id")
	private String leadId;

	@Column(name = "heirarchy_start_date")
	private Timestamp heirarchyStartDate;

	@Column(name = "heirarchy_end_date")
	private Timestamp heirarchyEndDate;

	@Column(name = "stakeholders_id")
	private String stakeholdersId;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getPersonIntakeId() {
		return personIntakeId;
	}

	public void setPersonIntakeId(String personIntakeId) {
		this.personIntakeId = personIntakeId;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public Timestamp getHeirarchyStartDate() {
		return heirarchyStartDate;
	}

	public void setHeirarchyStartDate(Timestamp heirarchyStartDate) {
		this.heirarchyStartDate = heirarchyStartDate;
	}

	public Timestamp getHeirarchyEndDate() {
		return heirarchyEndDate;
	}

	public void setHeirarchyEndDate(Timestamp heirarchyEndDate) {
		this.heirarchyEndDate = heirarchyEndDate;
	}

	public String getStakeholdersId() {
		return stakeholdersId;
	}

	public void setStakeholdersId(String stakeholdersId) {
		this.stakeholdersId = stakeholdersId;
	}

}
