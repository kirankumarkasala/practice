package com.excelra.glpg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendor_details")
public class VendorDetails {

	@Id
	@Column(name = "uid")
	private String uId;

	@Column(name = "vendor_name")
	private String vendorName;

	@Column(name = "vendor_details")
	private String vendorDetails;

	@Column(name = "vendor_type")
	private String vendorType;

	@Column(name = "comments")
	private String comments;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	

}
