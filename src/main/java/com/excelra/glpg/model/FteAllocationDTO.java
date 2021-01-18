package com.excelra.glpg.model;

import java.util.Date;

public class FteAllocationDTO {

	private String uId;
	private float personIntakeId;
	private float management;
	private String consulting;
	private Date resourcesDateFrom;
	private Date resourcesDateTo;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public float getPersonIntakeId() {
		return personIntakeId;
	}

	public void setPersonIntakeId(float personIntakeId) {
		this.personIntakeId = personIntakeId;
	}

	public float getManagement() {
		return management;
	}

	public void setManagement(float management) {
		this.management = management;
	}

	public String getConsulting() {
		return consulting;
	}

	public void setConsulting(String consulting) {
		this.consulting = consulting;
	}

	public Date getResourcesDateFrom() {
		return resourcesDateFrom;
	}

	public void setResourcesDateFrom(Date resourcesDateFrom) {
		this.resourcesDateFrom = resourcesDateFrom;
	}

	public Date getResourcesDateTo() {
		return resourcesDateTo;
	}

	public void setResourcesDateTo(Date resourcesDateTo) {
		this.resourcesDateTo = resourcesDateTo;
	}

}
