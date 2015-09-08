package com.thevolume360.domain.search;

import javax.persistence.Embedded;

import com.thevolume360.domain.FullName;
import com.thevolume360.domain.enums.Gender;

public class LabourSearchCmd {
	@Embedded
	private FullName fullName;
	private String contactNumber;
	private Gender gender;

	public LabourSearchCmd() {
		this(new FullName(), "", null);
	}

	public LabourSearchCmd(FullName fullName, String contactNumber, Gender gender) {
		super();
		this.fullName = fullName;
		this.contactNumber = contactNumber;
		this.gender = gender;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "LabourSearchCmd [fullName=" + fullName + ", contactNumber=" + contactNumber + ", gender=" + gender
				+ "]";
	}

}
