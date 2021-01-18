package com.excelra.glpg.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProjectMasterDTO implements Serializable {

	private String uId;
	private String glpgProjectId;
	private String glpgCompoundId;
	private Date startDate;
	private Date dateOfCompletion;
	private Date estimatedDateEnd;
	private Date actualDateEnd;
	private String status;
	private AreaMasterDTO areaMasterDTO;
	private LeadDto Lead;
	private String title;
	private String isDevelopment;
	private PrioritizationMasterDTO priority;
	private RiskMasterDTO risks;
	private String goals;
	private String description;
	private String businessValues;
	private String inScope;
	private String outScope;
	private String mileStones;
	private String deliverables;
	private String comments;
	private LeadDto personInTake;
	private Date personInTakeRoleStartDate;
	private Date personInTakeRoleEndDate;
	private Date leadRoleStartDate;
	private Date leadRoleendDate;
	private LeadDto management;
	private String consulting;
	private Float fteManagment;
	private Float fteIntakePerson;
	private String fteConsulting;
	private Date fteStartDate;
	private Date fteEndDate;
	private String licenseUsed;
	private String vendorName;
	private String vendorDetails;
	private String vendorType;
	private String vendorComments;
	private List<LeadDto> sponsors;
	private List<LeadDto> StakeHolders;
	
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
	
	public Float getFteIntakePerson() {
		return fteIntakePerson;
	}
	public void setFteIntakePerson(Float fteIntakePerson) {
		this.fteIntakePerson = fteIntakePerson;
	}
	public String getGlpgCompoundId() {
		return glpgCompoundId;
	}
	public void setGlpgCompoundId(String glpgCompoundId) {
		this.glpgCompoundId = glpgCompoundId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getDateOfCompletion() {
		return dateOfCompletion;
	}
	public void setDateOfCompletion(Date dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AreaMasterDTO getAreaMasterDTO() {
		return areaMasterDTO;
	}
	public void setAreaMasterDTO(AreaMasterDTO areaMasterDTO) {
		this.areaMasterDTO = areaMasterDTO;
	}
	public LeadDto getLead() {
		return Lead;
	}
	public void setLead(LeadDto lead) {
		Lead = lead;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsDevelopment() {
		return isDevelopment;
	}
	public void setIsDevelopment(String isDevelopment) {
		this.isDevelopment = isDevelopment;
	}
	public PrioritizationMasterDTO getPriority() {
		return priority;
	}
	public void setPriority(PrioritizationMasterDTO priority) {
		this.priority = priority;
	}
	public RiskMasterDTO getRisks() {
		return risks;
	}
	public void setRisks(RiskMasterDTO risks) {
		this.risks = risks;
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
	public String getBusinessValues() {
		return businessValues;
	}
	public void setBusinessValues(String businessValues) {
		this.businessValues = businessValues;
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
	public String getMileStones() {
		return mileStones;
	}
	public void setMileStones(String mileStones) {
		this.mileStones = mileStones;
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
	public LeadDto getPersonInTake() {
		return personInTake;
	}
	public void setPersonInTake(LeadDto personInTake) {
		this.personInTake = personInTake;
	}
	public Date getPersonInTakeRoleStartDate() {
		return personInTakeRoleStartDate;
	}
	public void setPersonInTakeRoleStartDate(Date personInTakeRoleStartDate) {
		this.personInTakeRoleStartDate = personInTakeRoleStartDate;
	}
	public Date getPersonInTakeRoleEndDate() {
		return personInTakeRoleEndDate;
	}
	public void setPersonInTakeRoleEndDate(Date personInTakeRoleEndDate) {
		this.personInTakeRoleEndDate = personInTakeRoleEndDate;
	}
	public Date getLeadRoleStartDate() {
		return leadRoleStartDate;
	}
	public void setLeadRoleStartDate(Date leadRoleStartDate) {
		this.leadRoleStartDate = leadRoleStartDate;
	}
	public Date getLeadRoleendDate() {
		return leadRoleendDate;
	}
	public void setLeadRoleendDate(Date leadRoleendDate) {
		this.leadRoleendDate = leadRoleendDate;
	}
	public LeadDto getManagement() {
		return management;
	}
	public void setManagement(LeadDto management) {
		this.management = management;
	}
	public String getConsulting() {
		return consulting;
	}
	public void setConsulting(String consulting) {
		this.consulting = consulting;
	}
	public Float getFteManagment() {
		return fteManagment;
	}
	public void setFteManagment(Float fteManagment) {
		this.fteManagment = fteManagment;
	}
	public String getFteConsulting() {
		return fteConsulting;
	}
	public void setFteConsulting(String fteConsulting) {
		this.fteConsulting = fteConsulting;
	}
	public Date getFteStartDate() {
		return fteStartDate;
	}
	public void setFteStartDate(Date fteStartDate) {
		this.fteStartDate = fteStartDate;
	}
	public Date getFteEndDate() {
		return fteEndDate;
	}
	public void setFteEndDate(Date fteEndDate) {
		this.fteEndDate = fteEndDate;
	}
	public String getLicenseUsed() {
		return licenseUsed;
	}
	public void setLicenseUsed(String licenseUsed) {
		this.licenseUsed = licenseUsed;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorDetails() {
		return vendorDetails;
	}
	public void setVendorDetails(String vendorDetails) {
		this.vendorDetails = vendorDetails;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getVendorComments() {
		return vendorComments;
	}
	public void setVendorComments(String vendorComments) {
		this.vendorComments = vendorComments;
	}
	public List<LeadDto> getSponsors() {
		return sponsors;
	}
	public void setSponsors(List<LeadDto> sponsors) {
		this.sponsors = sponsors;
	}
	public List<LeadDto> getStakeHolders() {
		return StakeHolders;
	}
	public void setStakeHolders(List<LeadDto> stakeHolders) {
		StakeHolders = stakeHolders;
	}

}
