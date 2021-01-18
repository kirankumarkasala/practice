package com.excelra.glpg.model;

import java.util.Date;

public class BasicInfoDTO {

	private String uId;
	private String glpgProjectId;
	private String title;
	private Date startDate;
	private Date estimatedDateEnd;
	private Date actualDateEnd;
	private String area;
	private String isDevelopment;
	private String status;
	private String taPrioritizationValue;
	private String risksCode;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEstimatedDateEnd() {
		return estimatedDateEnd;
	}
	public void setEstimatedDateEnd(Date estimatedDateEnd) {
		this.estimatedDateEnd = estimatedDateEnd;
	}
	public Date getActualDateEnd() {
		return actualDateEnd;
	}
	public void setActualDateEnd(Date actualDateEnd) {
		this.actualDateEnd = actualDateEnd;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public String getTaPrioritizationValue() {
		return taPrioritizationValue;
	}
	public void setTaPrioritizationValue(String taPrioritizationValue) {
		this.taPrioritizationValue = taPrioritizationValue;
	}
	public String getRisksCode() {
		return risksCode;
	}
	public void setRisksCode(String risksCode) {
		this.risksCode = risksCode;
	}
	
}
