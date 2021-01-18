package com.excelra.glpg.model;

public class MilestonesDelivDTO {

	private String uId;
	private String milestones;
	private String deliverables;
	private String comments;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
