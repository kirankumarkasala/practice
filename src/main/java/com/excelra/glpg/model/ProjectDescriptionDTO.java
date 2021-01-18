package com.excelra.glpg.model;

public class ProjectDescriptionDTO {

	private String uId;
	private String goals;
	private String description;
	private String businessValue;
	private String inScope;
	private String outScope;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
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

}
