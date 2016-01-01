package com.thevolume360.domain.search;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.thevolume360.domain.FullName;
import com.thevolume360.domain.enums.Designation;

public class OfficeWorkerSearchCmd {

	@Embedded
	private FullName fullName;

	@Enumerated(EnumType.STRING)
	private Designation designation;

	private String contactNumber;

	public OfficeWorkerSearchCmd() {
	}

	public OfficeWorkerSearchCmd(FullName fullName, Designation designation, String contactNumber) {
		this.fullName = fullName;
		this.designation = designation;
		this.contactNumber = contactNumber;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "OfficeWorkerSearchCmd [fullName=" + fullName + ", designation=" + designation + ", contactNumber="
				+ contactNumber + "]";
	}

}
