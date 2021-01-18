package com.excelra.glpg.model;

import java.util.Date;

public class AdvanceSearch {

	private String glpgProjectId;
	private String leadId;
	private String intakePersonId;
	private Date fromDate;
	private Date toDate;
	private String areaId;

	public String getGlpgProjectId() {
		return glpgProjectId;
	}

	public void setGlpgProjectId(String glpgProjectId) {
		this.glpgProjectId = glpgProjectId;
	}

	public String getLeadId() {
		return leadId;
	}
	

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getIntakePersonId() {
		return intakePersonId;
	}

	public void setIntakePersonId(String intakePersonId) {
		this.intakePersonId = intakePersonId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
