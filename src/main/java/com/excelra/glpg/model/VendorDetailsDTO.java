package com.excelra.glpg.model;

public class VendorDetailsDTO {

	private String uId;
	private String licenseUsed;
	private String vendorName;
	private String vendorDetails;
	private String vendorType;
	private String vendorComments;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
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

}
