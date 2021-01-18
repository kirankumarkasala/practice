package com.excelra.glpg.model;

import java.util.Date;

public class IntakePersonLeadDTO {

	private String uId;
	private String intakePerson;
	private String lead;
	private Date personRoleStartDate;
	private Date personRoleEndDate;
	private Date leadRoleStartDate;
	private Date leadRoleEndDate;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getIntakePerson() {
		return intakePerson;
	}

	public void setIntakePerson(String intakePerson) {
		this.intakePerson = intakePerson;
	}

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public Date getPersonRoleStartDate() {
		return personRoleStartDate;
	}

	public void setPersonRoleStartDate(Date personRoleStartDate) {
		this.personRoleStartDate = personRoleStartDate;
	}

	public Date getPersonRoleEndDate() {
		return personRoleEndDate;
	}

	public void setPersonRoleEndDate(Date personRoleEndDate) {
		this.personRoleEndDate = personRoleEndDate;
	}

	public Date getLeadRoleStartDate() {
		return leadRoleStartDate;
	}

	public void setLeadRoleStartDate(Date leadRoleStartDate) {
		this.leadRoleStartDate = leadRoleStartDate;
	}

	public Date getLeadRoleEndDate() {
		return leadRoleEndDate;
	}

	public void setLeadRoleEndDate(Date leadRoleEndDate) {
		this.leadRoleEndDate = leadRoleEndDate;
	}

}
