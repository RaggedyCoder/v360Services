package com.thevolume360.domain.enums;

public enum WorkType {
	CIVIL(1, "Civil", "CIV"), ELECTRICAL(2, "Electrical", "ELEC"), FIREWORKS(3,
			"Fire Works", "FW"), INTERIOR(4, "Interior", "INT"), MIXED(5,
			"Mixed", "MIX");

	private WorkType(int id, String name, String shortCode) {
		this.id = id;
		this.name = name;
		this.shortCode = shortCode;
	}

	private int id;
	private String name;
	private String shortCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
