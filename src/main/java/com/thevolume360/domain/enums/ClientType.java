package com.thevolume360.domain.enums;

public enum ClientType {
	COMPANY("Company", "CM"), PERSON("person", "PR");

	private String label;
	private String shortCode;

	private ClientType(String label, String shortCode) {
		this.label = label;
		this.shortCode = shortCode;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

}
