package com.excelra.glpg.model;

public class PersonIntakeDTO {

	private String personIntakeId;

	private String personName;

	public String getPersonIntakeId() {
		return personIntakeId;
	}

	public void setPersonIntakeId(String personIntakeId) {
		this.personIntakeId = personIntakeId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personIntakeId == null) ? 0 : personIntakeId.hashCode());
		result = prime * result + ((personName == null) ? 0 : personName.hashCode());
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
		PersonIntakeDTO other = (PersonIntakeDTO) obj;
		if (personIntakeId == null) {
			if (other.personIntakeId != null)
				return false;
		} else if (!personIntakeId.equals(other.personIntakeId))
			return false;
		if (personName == null) {
			if (other.personName != null)
				return false;
		} else if (!personName.equals(other.personName))
			return false;
		return true;
	}

	
}
