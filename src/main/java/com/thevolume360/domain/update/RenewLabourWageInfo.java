package com.thevolume360.domain.update;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.thevolume360.domain.LabourWageInfo;

public class RenewLabourWageInfo {

	@NotNull
	private Long projectLabourId;
	@NotNull
	private Date lastWageInfoExpiredDate;
	@NotNull
	private LabourWageInfo labourWageInfo;

	public RenewLabourWageInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public RenewLabourWageInfo(Long projectLabourId) {
		this.projectLabourId = projectLabourId;
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH,-1);
		lastWageInfoExpiredDate=calendar.getTime();
		labourWageInfo=new LabourWageInfo();
	}

	public Long getProjectLabourId() {
		return projectLabourId;
	}

	public void setProjectLabourId(Long projectLabourId) {
		this.projectLabourId = projectLabourId;
	}

	public Date getLastWageInfoExpiredDate() {
		return lastWageInfoExpiredDate;
	}

	public void setLastWageInfoExpiredDate(Date lastWageInfoExpiredDate) {
		this.lastWageInfoExpiredDate = lastWageInfoExpiredDate;
	}

	public LabourWageInfo getLabourWageInfo() {
		return labourWageInfo;
	}

	public void setLabourWageInfo(LabourWageInfo labourWageInfo) {
		this.labourWageInfo = labourWageInfo;
	}

	@Override
	public String toString() {
		return "RenewLabourWageInfo [projectLabourId=" + projectLabourId + ", lastWageInfoExpiredDate="
				+ lastWageInfoExpiredDate + ", labourWageInfo=" + labourWageInfo + "]";
	}
}
