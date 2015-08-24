package com.thevolume360.domain.enums;

public enum ClientType {
	COMPANY("Company"), PERSON("person");

	private String label;

	private ClientType(String label) {
		this.label = label;
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

}
