package com.thevolume360.domain.enums;

public enum RequiredNotRequired {
	REQUIRED(1,"Required","REQ"), NOT_REQUIRED(2,"Not Required","NREQ");

	private final String label;
	private final int id;
	private final String shortCode;

	private RequiredNotRequired(int id, String label, String shortCode) {
		this.label = label;
		this.id = id;
		this.shortCode = shortCode;
	}

	public String getLabel() {
		return label;
	}

	public int getId() {
		return id;
	}

	public String getShortCode() {
		return shortCode;
	}

}
