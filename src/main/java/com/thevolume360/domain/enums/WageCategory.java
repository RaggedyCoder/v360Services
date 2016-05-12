package com.thevolume360.domain.enums;

public enum WageCategory {
	SINGLE_UNIT("Single Unit", "SU"), SUMMATION_UNIT("Summation Unit", "SMU");

	private String name;
	private String shortCode;

	private WageCategory(String name, String shortCode) {
		this.name = name;
		this.shortCode = shortCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

}
