package com.excelra.glpg.model;

public class SearchOrderInputs {

	private Integer pageNo;
	private Integer limit;
	private String columnName;
	private String order;
	private String searchBy;
	private String searchName;
	private AdvanceSearch advanceSearch;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getSearchName() {
		return searchName;
	}

	public AdvanceSearch getAdvanceSearch() {
		return advanceSearch;
	}

	public void setAdvanceSearch(AdvanceSearch advanceSearch) {
		this.advanceSearch = advanceSearch;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
