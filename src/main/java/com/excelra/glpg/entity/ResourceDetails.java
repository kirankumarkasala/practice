package com.excelra.glpg.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource_details")
public class ResourceDetails {

	@Id
	@Column(name = "uid")
	private String uId;

	@Column(name = "person_intake_id")
	private String personIntakeId;

	@Column(name = "lead_id")
	private String leadId;

	@Column(name = "management_id")
	private String managementId;

	@Column(name = "consulting_id")
	private String consultingId;

	@Column(name = "person_intake_start_date")
	private Timestamp personIntakeStartDate;

	@Column(name = "person_intake_end_date")
	private Timestamp personIntakeEndDate;

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

	public String getManagementId() {
		return managementId;
	}

	public void setManagementId(String managementId) {
		this.managementId = managementId;
	}

	public String getConsultingId() {
		return consultingId;
	}

	public void setConsultingId(String consultingId) {
		this.consultingId = consultingId;
	}

	public Timestamp getPersonIntakeStartDate() {
		return personIntakeStartDate;
	}

	public void setPersonIntakeStartDate(Timestamp personIntakeStartDate) {
		this.personIntakeStartDate = personIntakeStartDate;
	}

	public Timestamp getPersonIntakeEndDate() {
		return personIntakeEndDate;
	}

	public void setPersonIntakeEndDate(Timestamp personIntakeEndDate) {
		this.personIntakeEndDate = personIntakeEndDate;
	}

}
