package com.excelra.glpg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "risk_master")
public class RiskMaster {

	@Id
	@Column(name = "risk_code")
	private String riskCode;

	@Column(name = "risk_detail")
	private String riskDetail;

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskDetail() {
		return riskDetail;
	}

	public void setRiskDetail(String riskDetail) {
		this.riskDetail = riskDetail;
	}

}
