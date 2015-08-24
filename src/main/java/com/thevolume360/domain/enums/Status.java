package com.thevolume360.domain.enums;

public enum Status {
	ACTIVE("Active"), INACTIVE("Inactive"), CLOSED("Closed");

	private Status(String status) {
		this.status = status;
	}

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}