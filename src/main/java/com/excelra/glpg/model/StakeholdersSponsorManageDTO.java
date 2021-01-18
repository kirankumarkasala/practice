package com.excelra.glpg.model;

public class StakeholdersSponsorManageDTO {

	private String uId;
	private String stakeholders;
	private String sponsors;
	private String managementName;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getStakeholders() {
		return stakeholders;
	}

	public void setStakeholders(String stakeholders) {
		this.stakeholders = stakeholders;
	}

	public String getSponsors() {
		return sponsors;
	}

	public void setSponsors(String sponsors) {
		this.sponsors = sponsors;
	}

	public String getManagementName() {
		return managementName;
	}

	public void setManagementName(String managementName) {
		this.managementName = managementName;
	}
}
