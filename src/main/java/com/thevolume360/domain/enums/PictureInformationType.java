package com.thevolume360.domain.enums;

public enum PictureInformationType {

	WORK_ORDER("Work Order"), BILLS_HARD_COPY("Bill hard Copy");

	private String label;

	PictureInformationType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
