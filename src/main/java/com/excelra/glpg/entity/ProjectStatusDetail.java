package com.excelra.glpg.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_status_detail")
public class ProjectStatusDetail {

	@Id
	@Column(name = "uid")
	private String uId;

	@Column(name = "glpg_project_id")
	private String glpgProjectId;

	@Column(name = "glpg_compound_ids")
	private String glpgCompoundIds;

	@Column(name = "start_date")
	private Timestamp startDate;

	@Column(name = "estimated_date_end")
	private Timestamp estimatedDateEnd;

	@Column(name = "actual_date_end")
	private Timestamp actualDateEnd;

	@Column(name = "is_development")
	private String isDevelopment;

	@Column(name = "status")
	private String status;

	@Column(name = "ta_prioritization_code")
	private String taPrioritizationCode;

	@Column(name = "risks_code")
	private String risksCode;

	@Column(name = "area_id")
	private String areaId;

	@Column(name = "comments_risks")
	private String commentsRisks;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getGlpgProjectId() {
		return glpgProjectId;
	}

	public void setGlpgProjectId(String glpgProjectId) {
		this.glpgProjectId = glpgProjectId;
	}

	public String getGlpgCompoundIds() {
		return glpgCompoundIds;
	}

	public void setGlpgCompoundIds(String glpgCompoundIds) {
		this.glpgCompoundIds = glpgCompoundIds;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEstimatedDateEnd() {
		return estimatedDateEnd;
	}

	public void setEstimatedDateEnd(Timestamp estimatedDateEnd) {
		this.estimatedDateEnd = estimatedDateEnd;
	}

	public Timestamp getActualDateEnd() {
		return actualDateEnd;
	}

	public void setActualDateEnd(Timestamp actualDateEnd) {
		this.actualDateEnd = actualDateEnd;
	}

	public String getIsDevelopment() {
		return isDevelopment;
	}

	public void setIsDevelopment(String isDevelopment) {
		this.isDevelopment = isDevelopment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaPrioritizationCode() {
		return taPrioritizationCode;
	}

	public void setTaPrioritizationCode(String taPrioritizationCode) {
		this.taPrioritizationCode = taPrioritizationCode;
	}

	public String getRisksCode() {
		return risksCode;
	}

	public void setRisksCode(String risksCode) {
		this.risksCode = risksCode;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCommentsRisks() {
		return commentsRisks;
	}

	public void setCommentsRisks(String commentsRisks) {
		this.commentsRisks = commentsRisks;
	}
	
}
