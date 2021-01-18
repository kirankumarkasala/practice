package com.excelra.glpg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_description")
public class ProjectDescription {

	@Id
	@Column(name = "uid")
	private String uId;

	@Column(name = "title")
	private String title;

	@Column(name = "goals")
	private String goals;

	@Column(name = "description")
	private String description;

	@Column(name = "business_value")
	private String businessValue;

	@Column(name = "in_scope")
	private String inScope;

	@Column(name = "out_scope")
	private String outScope;

	@Column(name = "comments")
	private String comments;

	@Column(name = "milestones")
	private String milestones;

	@Column(name = "deliverables")
	private String deliverables;
	


	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
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

	public String getInScope() {
		return inScope;
	}

	public void setInScope(String inScope) {
		this.inScope = inScope;
	}

	public String getOutScope() {
		return outScope;
	}

	public void setOutScope(String outScope) {
		this.outScope = outScope;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getMilestones() {
		return milestones;
	}

	public void setMilestones(String milestones) {
		this.milestones = milestones;
	}

	public String getDeliverables() {
		return deliverables;
	}

	public void setDeliverables(String deliverables) {
		this.deliverables = deliverables;
	}

}
