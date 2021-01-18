package com.excelra.glpg.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource_allocation")
public class ResourceAllocation {

	@Id
	@Column(name = "uid")
	private String uId;

	@Column(name = "resources_management")
	private float resourcesManagement;

	@Column(name = "resources_lead")
	private float resourcesLead;

	@Column(name = "resources_consulting")
	private String resourcesConsulting;

	@Column(name = "resource_licenses")
	private String resourceLicenses;

	@Column(name = "resources_date_from")
	private Timestamp resourcesDateFrom;

	@Column(name = "resources_date_to")
	private Timestamp resourcesDateTo;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public float getResourcesManagement() {
		return resourcesManagement;
	}

	public void setResourcesManagement(float resourcesManagement) {
		this.resourcesManagement = resourcesManagement;
	}

	public float getResourcesLead() {
		return resourcesLead;
	}

	public void setResourcesLead(float resourcesLead) {
		this.resourcesLead = resourcesLead;
	}

	public String getResourcesConsulting() {
		return resourcesConsulting;
	}

	public void setResourcesConsulting(String resourcesConsulting) {
		this.resourcesConsulting = resourcesConsulting;
	}

	public String getResourceLicenses() {
		return resourceLicenses;
	}

	public void setResourceLicenses(String resourceLicenses) {
		this.resourceLicenses = resourceLicenses;
	}

	public Timestamp getResourcesDateFrom() {
		return resourcesDateFrom;
	}

	public void setResourcesDateFrom(Timestamp resourcesDateFrom) {
		this.resourcesDateFrom = resourcesDateFrom;
	}

	public Timestamp getResourcesDateTo() {
		return resourcesDateTo;
	}

	public void setResourcesDateTo(Timestamp resourcesDateTo) {
		this.resourcesDateTo = resourcesDateTo;
	}


}
