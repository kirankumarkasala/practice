package com.excelra.glpg.model;

public class LeadDto {

	private String leadId;

	private String leadName;

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getLeadName() {
		return leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leadId == null) ? 0 : leadId.hashCode());
		result = prime * result + ((leadName == null) ? 0 : leadName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeadDto other = (LeadDto) obj;
		if (leadId == null) {
			if (other.leadId != null)
				return false;
		} else if (!leadId.equals(other.leadId))
			return false;
		if (leadName == null) {
			if (other.leadName != null)
				return false;
		} else if (!leadName.equals(other.leadName))
			return false;
		return true;
	}

}
