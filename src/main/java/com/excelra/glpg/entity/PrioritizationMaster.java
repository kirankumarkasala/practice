package com.excelra.glpg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prioritization_master")
public class PrioritizationMaster {

	@Id
	@Column(name = "ta_prioritization_code")
	private String taPrioritizationCode;

	@Column(name = "ta_prioritization_value")
	private String taPrioritizationValue;

	public String getTaPrioritizationCode() {
		return taPrioritizationCode;
	}

	public void setTaPrioritizationCode(String taPrioritizationCode) {
		this.taPrioritizationCode = taPrioritizationCode;
	}

	public String getTaPrioritizationValue() {
		return taPrioritizationValue;
	}

	public void setTaPrioritizationValue(String taPrioritizationValue) {
		this.taPrioritizationValue = taPrioritizationValue;
	}

}
