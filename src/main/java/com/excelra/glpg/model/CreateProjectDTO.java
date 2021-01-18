package com.excelra.glpg.model;

import java.io.Serializable;
import java.util.Date;

public class CreateProjectDTO implements Serializable {


	private String glpgProjectId;
	private Date startDate;
	private String isDevelopment;
	private String area;
	private String title;
	private String goals;
	private String description;
	private String businessValue;
	private String comments;
	private String deliverables;


	public String getGlpgProjectId() {
		return glpgProjectId;
	}

	public void setGlpgProjectId(String glpgProjectId) {
		this.glpgProjectId = glpgProjectId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getIsDevelopment() {
		return isDevelopment;
	}

	public void setIsDevelopment(String isDevelopment) {
		this.isDevelopment = isDevelopment;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBusinessValue() {
		return businessValue;
	}

	public void setBusinessValue(String businessValue) {
		this.businessValue = businessValue;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDeliverables() {
		return deliverables;
	}

	public void setDeliverables(String deliverables) {
		this.deliverables = deliverables;
	}

}
