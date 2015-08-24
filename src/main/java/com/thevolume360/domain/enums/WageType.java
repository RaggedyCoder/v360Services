package com.thevolume360.domain.enums;

public enum WageType {

	DAILY_TYPE(1, "DT", "Daily"), MONTHLY_TYPE(2, "MT", "Monthly");

	private WageType(int id, String shortCode, String name) {
		this.id = id;
		this.shortCode = shortCode;
		this.name = name;
	}

	private int id;
	private String shortCode;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
